package com.example.myplants.featureplant.domain

import com.example.myplants.featureplant.domain.plant.Plant
import kotlinx.coroutines.flow.Flow

interface PlantManagementService {
    fun getUpcomingPlants(): Flow<List<PlantWaterLogPair>>
    fun getHistory(): Flow<List<PlantWaterLogPair>>
    fun getForgottenPlants(): Flow<List<PlantWaterLogPair>>
    fun getPlant(plantId: String): Flow<Plant?>
    fun getPlantWaterLogPair(plantId: String, logId: String): Flow<PlantWaterLogPair?>
    suspend fun upsertPlant(plant: Plant)
    suspend fun deletePlant(plantId: String, photoKey: String?)
    suspend fun toggleWater(logId: String)
    suspend fun syncWaterAlarms()
    suspend fun sendDailyForgottenAlarms()
}
