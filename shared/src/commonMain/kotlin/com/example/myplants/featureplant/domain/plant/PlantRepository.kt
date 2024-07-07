package com.example.myplants.featureplant.domain.plant

import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    suspend fun upsertPlant(plant: Plant)
    fun getPlants(): Flow<List<Plant>>
    fun getPlant(plantId: String): Flow<Plant?>
    suspend fun deletePlant(plantId: String)
}
