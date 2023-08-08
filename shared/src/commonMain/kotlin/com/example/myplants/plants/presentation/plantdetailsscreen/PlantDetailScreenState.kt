package com.example.myplants.plants.presentation.plantdetailsscreen

import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

data class PlantDetailScreenState(
    val name: String? = null,
    val description: String? = null,
    val waterAmount: String? = null,
    val waterDays: Set<DayOfWeek> = emptySet(),
    val waterTime: LocalTime? = null,
    val plantPhotoKey: String? = null,
    val plantSize: PlantSize? = null,
    val isWatered: Boolean = false,
    val isEditing: Boolean = true
)
