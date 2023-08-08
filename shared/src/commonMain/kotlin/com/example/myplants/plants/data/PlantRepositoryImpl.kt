package com.example.myplants.plants.data

import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantDataSource
import com.example.myplants.plants.domain.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class PlantRepositoryImpl(
    private val plantDataSource: PlantDataSource
) : PlantRepository {
    override suspend fun upsertPlant(plant: Plant) {
        plantDataSource.insertPlant(plant.toPlantEntity())
    }

    override fun getPlants(): Flow<List<Plant>> {
        return plantDataSource.getAllPlants().mapLatest {
            it.map { plantEntity ->
                plantEntity.toPlant()
            }
        }
    }

    override suspend fun getPlant(plantId: String): Plant? {
        return plantDataSource.getPlantById(plantId)?.toPlant()
    }

    override suspend fun deletePlant(plantId: String) {
        plantDataSource.deletePlant(plantId)
    }

    override suspend fun savePlant(plant: Plant) {
        plantDataSource.insertPlant(plant.toPlantEntity())
    }
}
