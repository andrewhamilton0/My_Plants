package com.example.myplants.featureplant.domain

import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.waterlog.WaterLog
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface PlantManagementService {
    fun getUpcomingPlants(): Flow<List<PlantWaterLogPair>>
    fun getHistory(): Flow<List<PlantWaterLogPair>>
    fun getForgottenPlants(): Flow<List<PlantWaterLogPair>>
    suspend fun generateUpcomingWaterLogs()
    suspend fun upsertPlant(plant: Plant)
    suspend fun deletePlant(plantId: String)
    suspend fun toggleWater(waterLog: WaterLog)

}