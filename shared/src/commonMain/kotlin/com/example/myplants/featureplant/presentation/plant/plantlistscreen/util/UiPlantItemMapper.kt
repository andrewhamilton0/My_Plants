package com.example.myplants.featureplant.presentation.plant.plantlistscreen.util

import com.example.myplants.core.domain.DateUtil
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantItem
import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun Plant.toUiPlant(
    currentDate: LocalDate
): UiPlantItem {
    val lastDayWatered = waterHistory.lastOrNull()
    println("$id water history $waterHistory")
    val nextWaterDate = DateUtil.nextOccurrenceOfDay(currentDate, waterDays)!!
    val nextWaterDateFromCreationDate = DateUtil.nextOccurrenceOfDay(creationDate, waterDays)!!
    val nextWaterDateFromLastWateredDate = lastDayWatered?.let {
        DateUtil.nextOccurrenceOfDay(it, waterDays)
    }
    val prevWaterDateFromToday = DateUtil.previousOccurrenceOfDay(currentDate, waterDays)!!
    val wasWateredOnTime = lastDayWatered?.let { it >= prevWaterDateFromToday } ?: false
    val isOnFirstWater = nextWaterDateFromCreationDate == nextWaterDate
    val isCurrentlyWatered = lastDayWatered?.let {
        it in prevWaterDateFromToday..nextWaterDate
    } ?: false

    val waterStatus = if (wasWateredOnTime || isOnFirstWater) {
        WaterStatus.Upcoming(
            localDateTime = LocalDateTime(nextWaterDate, waterTime),
            dateDescriptor = DateDescriptor.getDateDescriptor(currentDate, nextWaterDate)
        )
    } else {
        val dateForgotten = nextWaterDateFromLastWateredDate ?: nextWaterDateFromCreationDate
        WaterStatus.Forgotten(
            localDateTime = LocalDateTime(dateForgotten, waterTime),
            dateDescriptor = DateDescriptor.getDateDescriptor(currentDate, dateForgotten)
        )
    }

    return UiPlantItem(
        id = id,
        description = description,
        isWatered = isCurrentlyWatered,
        name = name,
        photo = photo,
        waterAmount = waterAmount,
        plantSize = plantSize,
        waterStatus = waterStatus,
        waterHistory = waterHistory
    )
}
