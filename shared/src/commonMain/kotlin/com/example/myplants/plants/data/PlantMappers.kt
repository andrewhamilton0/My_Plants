package com.example.myplants.plants.data

import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.LocalDate
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
        isWatered = isWatered,
        plantSize = plantSize.name,
        photoKey = null, // TODO() LATER ADD PHOTO TO PLANT
        creationDate = creationDate.toEpochDays().toLong(),
        daysWatered = daysWatered
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
        isWatered = isWatered,
        plantSize = PlantSize.valueOf(plantSize),
        photo = null, // TODO LATER ADD PHOTO TO PLANT
        creationDate = LocalDate.fromEpochDays(creationDate.toInt()),
        daysWatered = daysWatered
    )
}
