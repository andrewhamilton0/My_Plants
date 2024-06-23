package com.example.myplants.plants.data

import com.example.myplants.PlantDatabase
import com.example.myplants.plants.domain.PlantDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import plantsdb.PlantEntity

class PlantDataSourceImpl(
    db: PlantDatabase
) : PlantDataSource {

    private val queries = db.plantEntityQueries
    override fun getPlantById(id: String): Flow<PlantEntity?> {
        return queries.getPlantById(id).asFlow().mapToOneOrNull()
    }

    override fun getAllPlants(): Flow<List<PlantEntity>> {
        return queries.getAllPlants().asFlow().mapToList()
    }

    override suspend fun deletePlant(id: String) {
        withContext(Dispatchers.IO) {
            queries.deletePlantById(id)
        }
    }

    override suspend fun insertPlant(plantEntity: PlantEntity) {
        withContext(Dispatchers.IO) {
            queries.insertPlant(
                id = plantEntity.id,
                name = plantEntity.name,
                description = plantEntity.description,
                waterAmount = plantEntity.waterAmount,
                waterDays = plantEntity.waterDays,
                waterTime = plantEntity.waterTime,
                isWatered = plantEntity.isWatered,
                plantSize = plantEntity.plantSize,
                photoKey = plantEntity.photoKey
            )
        }
    }
}
