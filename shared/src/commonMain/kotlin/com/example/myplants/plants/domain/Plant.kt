package com.example.myplants.plants.domain

import com.example.myplants.core.data.UuidProvider
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Plant(
    val id: String = UuidProvider.getUuid(),
    val name: String,
    val description: String,
    val waterAmount: String,
    val waterDays: Set<DayOfWeek>,
    val waterTime: LocalTime,
    val isWatered: Boolean,
    val plantSize: PlantSize,
    val photo: Photo?,
    val creationDate: LocalDate,
    val daysWatered: Set<LocalDate>
)
