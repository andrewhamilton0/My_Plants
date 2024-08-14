package com.example.myplants.featureplant.presentation.plant.plantlistscreen.util

import com.example.myplants.featureplant.domain.PlantWaterLogPair
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.UiPlantDetailItem
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantListItem
import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor
import kotlinx.datetime.LocalDate

fun PlantWaterLogPair.toUiPlantListItem(
    currentDate: LocalDate
): UiPlantListItem {
    return UiPlantListItem(
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

fun PlantWaterLogPair.toUiPlantDetailItem(): UiPlantDetailItem {
    return UiPlantDetailItem(
        plantId = plant.id,
        description = plant.description,
        name = plant.name,
        photo = plant.photo,
        plantSize = plant.plantSize,
        waterAmount = plant.waterAmount,
        isWatered = waterLog.isWatered,
        waterFrequencyWeekly = plant.waterDays.size
    )
}
