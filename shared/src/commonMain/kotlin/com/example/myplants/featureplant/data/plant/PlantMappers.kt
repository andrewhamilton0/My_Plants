package com.example.myplants.featureplant.data.plant

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
        photoKey = null // TODO() LATER ADD PHOTO TO PLANT
    )
}

fun PlantEntity.toPlant(): Plant {
    return Plant(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays,
        waterTime = LocalTime.fromNanosecondOfDay(waterTime),
        plantSize = PlantSize.valueOf(plantSize),
        photo = null // TODO LATER ADD PHOTO TO PLANT
    )
}
