package com.example.myplants.featureplant.data.plant

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantSize
import kotlinx.datetime.LocalTime
import plantsdb.PlantEntity

fun Plant.toPlantEntity(): PlantEntity {
    return PlantEntity(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays,
        waterTime = waterTime.toNanosecondOfDay(),
        plantSize = plantSize.name,
        photoKey = photo?.key
    )
}

fun PlantEntity.toPlant(photo: Photo?): Plant {
    return Plant(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays,
        waterTime = LocalTime.fromNanosecondOfDay(waterTime),
        plantSize = PlantSize.valueOf(plantSize),
        photo = photo
    )
}
