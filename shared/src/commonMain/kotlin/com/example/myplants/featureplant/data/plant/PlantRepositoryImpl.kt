package com.example.myplants.featureplant.data.plant

import com.example.myplants.core.data.util.PhotoStorageManager
import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantDataSource
import com.example.myplants.featureplant.domain.plant.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapLatest

class PlantRepositoryImpl(
    private val plantDataSource: PlantDataSource,
    private val photoStorageManager: PhotoStorageManager
) : PlantRepository {
    override suspend fun upsertPlant(plant: Plant) {
        plantDataSource.upsertPlant(plant.toPlantEntity())
        plant.photo?.let { photo ->
            upsertPhoto(photo.key, photo.byteArray)
        }
    }

    override fun getPlants(): Flow<List<Plant>> {
        return plantDataSource.getAllPlants().mapLatest {
            it.map { plantEntity ->
                val byteArray = plantEntity.photoKey?.let { key -> getPhoto(key) }
                val photo = byteArray?.let { array -> Photo(plantEntity.photoKey, array) }
                plantEntity.toPlant(photo)
            }
        }
    }

    override fun getPlant(plantId: String): Flow<Plant?> {
        return plantDataSource.getPlantById(plantId).mapLatest { plantEntity ->
            val byteArray = plantEntity?.photoKey?.let { key -> getPhoto(key) }
            val photo = byteArray?.let { array -> Photo(plantEntity.photoKey, array) }
            plantEntity?.toPlant(photo)
        }
    }

    override suspend fun deletePlant(plantId: String) {
        val photoKey = getPlant(plantId).firstOrNull()?.photo?.key
        photoKey?.let { deletePhoto(it) }
        plantDataSource.deletePlant(plantId)
    }

    private suspend fun getPhoto(photoKey: String): ByteArray? {
        return photoStorageManager.retrieveByteArrayFromInternalStorage("$photoKey.jpg")
    }

    private suspend fun upsertPhoto(photoKey: String, byteArray: ByteArray) {
        photoStorageManager.upsertByteArrayToInternalStorage("$photoKey.jpg", byteArray)
    }

    private suspend fun deletePhoto(photoKey: String) {
        photoStorageManager.deleteByteArrayFromInternalStorage("$photoKey.jpg")
    }
}
