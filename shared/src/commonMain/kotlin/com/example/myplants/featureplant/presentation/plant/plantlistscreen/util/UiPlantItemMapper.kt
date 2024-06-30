package com.example.myplants.featureplant.presentation.plant.plantlistscreen.util

import com.example.myplants.featureplant.domain.PlantWaterLogPair
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantItem
import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor
import kotlinx.datetime.LocalDate

fun PlantWaterLogPair.toUiPlant(
    currentDate: LocalDate
): UiPlantItem {
    return UiPlantItem(
        plantId = plant.id,
        description = plant.description,
        name = plant.name,
        photo = plant.photo,
        plantSize = plant.plantSize,
        waterAmount = plant.waterAmount,
        isWatered = waterLog.isWatered,
        logId = waterLog.id,
        dateDescriptor = DateDescriptor.getDateDescriptor(currentDate, waterLog.date)
    )
}
