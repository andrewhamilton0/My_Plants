package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.core.presentation.util.DateUtils
import com.example.myplants.plants.domain.Plant
import kotlinx.datetime.LocalDate

fun Plant.toUiPlant(
    currentDate: LocalDate
): UiPlantItem {
    val nextWaterDate = DateUtils.getNextWaterDate(this.waterDays, currentDate)
    val nextWaterDateDescriptor = DateUtils.getDateDescriptor(currentDate, nextWaterDate)

    return UiPlantItem(
        id = id,
        nextWaterDateDescriptor = nextWaterDateDescriptor,
        description = description,
        isWatered = isWatered,
        name = name,
        photo = photo,
        waterAmount = waterAmount,
        plantSize = plantSize
    )
}
