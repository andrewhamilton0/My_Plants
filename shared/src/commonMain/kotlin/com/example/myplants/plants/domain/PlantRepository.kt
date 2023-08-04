package com.example.myplants.plants.domain

import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    suspend fun upsertPlant(plant: Plant)
    fun getPlants(): Flow<List<Plant>>
}
