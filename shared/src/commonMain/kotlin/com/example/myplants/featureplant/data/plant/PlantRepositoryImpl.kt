package com.example.myplants.featureplant.data.plant

import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantDataSource
import com.example.myplants.featureplant.domain.plant.PlantRepository
import kotlinx.coroutines.flow.Flow
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

    override fun getPlant(plantId: String): Flow<Plant?> {
        return plantDataSource.getPlantById(plantId).mapLatest {
            it?.toPlant()
        }
    }

    override suspend fun deletePlant(plantId: String) {
        plantDataSource.deletePlant(plantId)
    }

    override suspend fun savePlant(plant: Plant) {
        plantDataSource.insertPlant(plant.toPlantEntity())
    }
}
