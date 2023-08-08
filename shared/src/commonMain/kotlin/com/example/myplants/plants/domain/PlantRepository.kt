package com.example.myplants.plants.domain

import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    suspend fun upsertPlant(plant: Plant)
    fun getPlants(): Flow<List<Plant>>
    suspend fun getPlant(plantId: String): Plant?
    suspend fun deletePlant(plantId: String)
    suspend fun savePlant(plant: Plant)
}
