package com.example.myplants.featureplant.presentation.plant.editplantscreen

import com.example.myplants.featureplant.domain.plant.Plant

fun Plant.toUiEditPlant(): UiEditPlantItem {
    return UiEditPlantItem(
        id = id,
        photo = photo,
        description = description,
        name = name,
        plantSize = plantSize,
        waterDays = waterDays,
        waterTime = waterTime,
        waterAmount = waterAmount
    )
}

fun UiEditPlantItem.toPlant(): Plant {
    return if (id != null) {
        Plant(
            id = id,
            photo = photo,
            description = description,
            name = name,
            plantSize = plantSize,
            waterDays = waterDays,
            waterTime = waterTime,
            waterAmount = waterAmount
        )
    } else {
        Plant(
            photo = photo,
            description = description,
            name = name,
            plantSize = plantSize,
            waterDays = waterDays,
            waterTime = waterTime,
            waterAmount = waterAmount
        )
    }
}
