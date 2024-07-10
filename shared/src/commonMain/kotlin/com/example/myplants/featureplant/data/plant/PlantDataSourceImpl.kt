package com.example.myplants.featureplant.data.plant

import com.example.myplants.PlantDatabase
import com.example.myplants.featureplant.domain.plant.PlantDataSource
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

    override suspend fun upsertPlant(plantEntity: PlantEntity) {
        withContext(Dispatchers.IO) {
            queries.upsertPlant(
                id = plantEntity.id,
                name = plantEntity.name,
                description = plantEntity.description,
                waterAmount = plantEntity.waterAmount,
                waterDays = plantEntity.waterDays,
                waterTime = plantEntity.waterTime,
                plantSize = plantEntity.plantSize,
                photoKey = plantEntity.photoKey
            )
        }
    }
}
