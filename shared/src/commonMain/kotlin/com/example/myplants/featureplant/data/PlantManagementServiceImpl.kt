package com.example.myplants.featureplant.data

import com.example.myplants.core.domain.DateUtil
import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.domain.PlantWaterLogPair
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.waterlog.WaterLog
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest

class PlantManagementServiceImpl(
    private val plantRepository: PlantRepository,
    private val waterLogRepository: WaterLogRepository
) : PlantManagementService {

    private val currentDate = DateUtil.getCurrentDateTime().mapLatest { it.date }
    private val waterLogs = waterLogRepository.getAllWaterLogs()
    private val plants = plantRepository.getPlants()

    override fun getUpcomingPlants(): Flow<List<PlantWaterLogPair>> {
        return combine(plants, waterLogs, currentDate) { _plants, _waterLogs, _currentDate ->
            _plants.flatMap { plant ->
                val nextWaterDate = DateUtil.nextOccurrenceOfDay(_currentDate, plant.waterDays)
                _waterLogs.filter { it.plantId == plant.id && it.date == nextWaterDate }.map {
                    PlantWaterLogPair(plant, it)
                }
            }
        }
    }

    override fun getHistory(): Flow<List<PlantWaterLogPair>> {
        return combine(waterLogs, plants) { logs, plants ->
            plants.flatMap { plant ->
                logs.filter { it.plantId == plant.id }.map {
                    PlantWaterLogPair(plant, it)
                }
            }
        }
    }

    override fun getForgottenPlants(): Flow<List<PlantWaterLogPair>> {
        return combine(waterLogs, plants) { logs, plants ->
            plants.flatMap { plant ->
                logs.filter { it.plantId == plant.id && !it.isWatered }.map {
                    PlantWaterLogPair(plant, it)
                }
            }
        }
    }

    // TODO IMPLEMENT THIS TO GENERATE AT THE START OF EVERYDAY
    override suspend fun generateUpcomingWaterLogs() {
        val plants = plantRepository.getPlants().first()
        val logs = waterLogRepository.getAllWaterLogs().first()
        plants.forEach { plant ->
            val nextWaterDate = DateUtil.nextOccurrenceOfDay(currentDate.first(), plant.waterDays)
            nextWaterDate?.let { waterDate ->
                if (logs.none { it.plantId == plant.id && it.date == waterDate }) {
                    waterLogRepository.upsertWaterLog(
                        WaterLog(
                            plantId = plant.id,
                            date = waterDate,
                            isWatered = false
                        )
                    )
                }
            } ?: run {
                println("Error generating water logs in Plant Management Service")
                println("No water days for ${plant.name}")
            }
        }
    }

    override suspend fun upsertPlant(plant: Plant) {
        plantRepository.upsertPlant(plant)
        deleteUpcomingWaterLog(plant.id)
        generateUpcomingWaterLogs()
    }

    override suspend fun deletePlant(plantId: String) {
        plantRepository.deletePlant(plantId)
    }

    override suspend fun toggleWater(logId: String) {
        waterLogRepository.toggleWater(logId)
    }

    private suspend fun deleteUpcomingWaterLog(plantId: String) {
        getUpcomingPlants().first().filter {
            it.plant.id == plantId && it.waterLog.date >= currentDate.first()
        }.forEach { waterLogRepository.deleteWaterLog(it.waterLog.id) }
    }
}
