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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.LocalDate

class PlantManagementServiceImpl(
    private val plantRepository: PlantRepository,
    private val waterLogRepository: WaterLogRepository,
    private val currentDate: Flow<LocalDate> = DateUtil.getCurrentDateTime().map { it.date }
) : PlantManagementService {

    private val waterLogs = waterLogRepository.getAllWaterLogs()
    private val plants = plantRepository.getPlants()

    override fun getUpcomingPlants(): Flow<List<PlantWaterLogPair>> {
        return combine(plants, waterLogs, currentDate) { plants, waterLogs, currentDate ->
            generateUpcomingWaterLogs()
            waterLogs.filter { it.date >= currentDate }
                .map { log -> PlantWaterLogPair(plants.first { log.plantId == it.id }, log) }
                .sortedWith(compareBy({ it.waterLog.date }, { it.plant.name.lowercase() }))
        }
    }

    override fun getHistory(): Flow<List<PlantWaterLogPair>> {
        return combine(waterLogs, plants, currentDate) { logs, plants, currentDate ->
            generateUpcomingWaterLogs()
            logs.filter { it.date < currentDate }
                .map { log -> PlantWaterLogPair(plants.first { log.plantId == it.id }, log) }
                .sortedWith(compareByDescending<PlantWaterLogPair> { it.waterLog.date }.thenBy { it.plant.name.lowercase() })
        }
    }

    override fun getForgottenPlants(): Flow<List<PlantWaterLogPair>> {
        return combine(waterLogs, plants, currentDate) { logs, plants, currentDate ->
            generateUpcomingWaterLogs()
            plants.flatMap { plant ->
                val lastWaterDate = DateUtil.previousOccurrenceOfDay(currentDate, plant.waterDays)
                logs.filter { it.plantId == plant.id && !it.isWatered && it.date == lastWaterDate }
                    .map { PlantWaterLogPair(plant, it) }
            }.sortedWith(
                compareByDescending<PlantWaterLogPair> { it.waterLog.date }
                    .thenBy { it.plant.name.lowercase() }
            )
        }
    }

    override fun getPlant(plantId: String): Flow<Plant?> {
        return plantRepository.getPlant(plantId)
    }

    override fun getPlantWaterLogPair(plantId: String, logId: String): Flow<PlantWaterLogPair?> {
        val plant = getPlant(plantId)
        val waterLog = waterLogRepository.getWaterLog(logId)
        return combine(plant, waterLog) { _plant, _waterLog ->
            _plant?.takeIf { _waterLog != null }?.let { PlantWaterLogPair(it, _waterLog!!) }
        }
    }

    override suspend fun upsertPlant(plant: Plant) {
        plantRepository.upsertPlant(plant)
        deleteUpcomingWaterLog(plant.id)
        generateUpcomingWaterLogs()
    }

    override suspend fun deletePlant(plantId: String, photoKey: String?) {
        plantRepository.deletePlant(plantId, photoKey)
    }

    override suspend fun toggleWater(logId: String) {
        waterLogRepository.toggleWater(logId)
    }

    private suspend fun deleteUpcomingWaterLog(plantId: String) {
        getUpcomingPlants().first().filter {
            it.plant.id == plantId && it.waterLog.date >= currentDate.first()
        }.forEach { waterLogRepository.deleteWaterLog(it.waterLog.id) }
    }

    // TODO IMPLEMENT THIS TO GENERATE AT THE START OF EVERYDAY
    private val mutex = Mutex()
    private suspend fun generateUpcomingWaterLogs() {
        mutex.withLock {
            val plants = plantRepository.getPlants().first()
            val logs = waterLogRepository.getAllWaterLogs().first()
            val currentDate = currentDate.first()

            plants.forEach { plant ->
                val nextWaterDate = DateUtil.nextOccurrenceOfDay(currentDate, plant.waterDays)
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
    }
}
