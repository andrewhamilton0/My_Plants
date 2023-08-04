package com.example.myplants.plants.domain

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

data class Plant(
    val id: String,
    val name: String,
    val description: String,
    val waterAmount: String,
    val waterDays: Set<DayOfWeek>,
    val waterTime: LocalTime,
    val isWatered: Boolean,
    val plantSize: PlantSize,
    val photoKey: String?
)
