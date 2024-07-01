package com.example.myplants.featureplant.domain.plant

import com.example.myplants.core.data.UuidProvider
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

data class Plant(
    val id: String = UuidProvider.getUuid(),
    val name: String,
    val description: String,
    val waterAmount: String,
    val waterDays: Set<DayOfWeek>,
    val waterTime: LocalTime,
    val plantSize: PlantSize,
    val photo: Photo?
)
