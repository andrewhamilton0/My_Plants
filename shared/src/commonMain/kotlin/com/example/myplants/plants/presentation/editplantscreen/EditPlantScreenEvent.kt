package com.example.myplants.plants.presentation.editplantscreen

import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

sealed class EditPlantScreenEvent {
    data class UpdateName(val name: String) : EditPlantScreenEvent()
    data class UpdateWaterDays(val waterDays: Set<DayOfWeek>) : EditPlantScreenEvent()
    data class UpdateTime(val time: LocalTime) : EditPlantScreenEvent()
    data class UpdateWaterAmount(val waterAmount: String) : EditPlantScreenEvent()
    data class UpdatePlantSize(val plantSize: PlantSize) : EditPlantScreenEvent()
    data class UpdateDescription(val description: String) : EditPlantScreenEvent()
    object SavePlant : EditPlantScreenEvent()
}
