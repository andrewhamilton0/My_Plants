package com.example.myplants.featureplant.data.plant

import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakePlantRepositoryImpl : PlantRepository {

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    private val plants = _plants.asStateFlow()

    override suspend fun upsertPlant(plant: Plant) {
        val currentList = _plants.value.toMutableList()
        currentList.removeAll { it.id == plant.id }
        currentList.add(plant)
        _plants.value = currentList
    }

    override fun getPlants(): Flow<List<Plant>> {
        return plants
    }

    override fun getPlant(plantId: String): Flow<Plant?> {
        return plants.map { plants ->
            plants.find { it.id == plantId }
        }
    }

    override suspend fun deletePlant(plantId: String, photoKey: String?) {
        val currentList = _plants.value.toMutableList()
        currentList.removeAll { it.id == plantId }
        _plants.value = currentList
    }
}
