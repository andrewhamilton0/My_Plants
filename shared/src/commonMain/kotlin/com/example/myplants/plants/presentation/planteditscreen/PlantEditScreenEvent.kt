package com.example.myplants.plants.presentation.planteditscreen

import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

sealed class PlantEditScreenEvent {
    data class UpdateName(val name: String) : PlantEditScreenEvent()
    data class UpdateWaterDays(val waterDays: Set<DayOfWeek>) : PlantEditScreenEvent()
    data class UpdateTime(val time: LocalTime) : PlantEditScreenEvent()
    data class UpdateWaterAmount(val waterAmount: String) : PlantEditScreenEvent()
    data class UpdatePlantSize(val plantSize: PlantSize) : PlantEditScreenEvent()
    data class UpdateDescription(val description: String) : PlantEditScreenEvent()
}