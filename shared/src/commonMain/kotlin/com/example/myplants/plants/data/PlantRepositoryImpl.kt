package com.example.myplants.plants.data

import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantRepository

class PlantRepositoryImpl : PlantRepository {
    override suspend fun upsertPlant(plant: Plant) {
    }
}
