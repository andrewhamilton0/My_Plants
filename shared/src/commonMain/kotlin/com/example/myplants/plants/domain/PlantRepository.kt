package com.example.myplants.plants.domain

interface PlantRepository {
    suspend fun upsertPlant(plant: Plant)
}
