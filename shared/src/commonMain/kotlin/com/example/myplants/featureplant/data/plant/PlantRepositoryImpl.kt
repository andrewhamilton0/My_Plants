package com.example.myplants.featureplant.data.plant

import com.example.myplants.core.data.util.PhotoStorageManager
import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantDataSource
import com.example.myplants.featureplant.domain.plant.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class PlantRepositoryImpl(
    private val plantDataSource: PlantDataSource,
    private val photoStorageManager: PhotoStorageManager
) : PlantRepository {
    override suspend fun upsertPlant(plant: Plant) {
        plantDataSource.insertPlant(plant.toPlantEntity())
        plant.photo?.let { photo ->
            savePhoto(photo.key, photo.byteArray)
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
        plantDataSource.deletePlant(plantId)
    }

    private suspend fun getPhoto(photoKey: String): ByteArray? {
        return photoStorageManager.retrieveByteArrayFromInternalStorage("${photoKey}.jpg")
    }

    private suspend fun savePhoto(photoKey: String, byteArray: ByteArray) {
        photoStorageManager.saveByteArrayToInternalStorage("${photoKey}.jpg", byteArray)
    }
}
