package com.example.myplants.plants.domain

import kotlinx.coroutines.flow.Flow
import plantsdb.PlantEntity

interface PlantDataSource {

    fun getPlantById(id: String): Flow<PlantEntity?>

    fun getAllPlants(): Flow<List<PlantEntity>>

    suspend fun deletePlant(id: String)

    suspend fun insertPlant(plantEntity: PlantEntity)
}
