package com.example.myplants.plants.data

import com.example.myplants.plants.domain.Plant
import plantsdb.PlantEntity

fun Plant.toPlantEntity(): PlantEntity {
    return PlantEntity(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays,
        waterTime = waterTime,
        isWatered = isWatered,
        plantSize = plantSize,
        photoKey = photoKey
    )
}

fun PlantEntity.toPlant(): Plant {
    return Plant(
        id = id,
        name = name,
        description = description,
        waterAmount = waterAmount,
        waterDays = waterDays,
        waterTime = waterTime,
        isWatered = isWatered,
        plantSize = plantSize,
        photoKey = photoKey
    )
}
