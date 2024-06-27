package com.example.myplants.featureplant.domain.plant

import com.example.myplants.featureplant.domain.waterlog.WaterLog
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface PlantRepository {
    suspend fun upsertPlant(plant: Plant)
    fun getPlants(): Flow<List<Plant>>
    fun getPlant(plantId: String): Flow<Plant?>
    suspend fun deletePlant(plantId: String)
    suspend fun savePlant(plant: Plant)
    suspend fun toggleWater(plantId: String, dayWatered: LocalDate)

}
