package com.example.myplants.plants.data

import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime
import plantsdb.PlantEntity

fun Plant.toPlantEntity(): PlantEntity {
    return PlantEntity(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays.map { it.name },
        waterTime = waterTime.toString(),
        isWatered = isWatered,
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
        waterDays = waterDays.map { DayOfWeek.valueOf(it) }.toSet(),
        waterTime = LocalTime.parse(waterTime),
        isWatered = isWatered,
        plantSize = PlantSize.valueOf(plantSize),
        photo = null // TODO LATER ADD PHOTO TO PLANT
    )
}
