package com.example.myplants.featureplant.presentation.plant.editplantscreen

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

data class UiEditPlantItem(
    val id: String?,
    val name: String,
    val description: String,
    val waterAmount: String,
    val waterDays: Set<DayOfWeek>,
    val waterTime: LocalTime,
    val plantSize: PlantSize,
    val photo: Photo?
)
