package com.example.myplants.plants.presentation.plantdetailsscreen

import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

sealed class PlantDetailScreenEvent {
    data class SavePlant(val plant: Plant) : PlantDetailScreenEvent()
    object EditButtonPressed : PlantDetailScreenEvent()
    object BackButtonPressed : PlantDetailScreenEvent()
    object ToggleWaterButton : PlantDetailScreenEvent()
    object ChangeImageButtonPressed : PlantDetailScreenEvent()
    data class NameEdited(val name: String) : PlantDetailScreenEvent()
    data class WaterDaysEdited(val waterDays: Set<DayOfWeek>) : PlantDetailScreenEvent()
    data class TimeEdited(val time: LocalTime) : PlantDetailScreenEvent()
    data class WaterAmountEdited(val waterAmount: String) : PlantDetailScreenEvent()
    data class PlantSizeEdited(val plantSize: PlantSize) : PlantDetailScreenEvent()
    data class DescriptionEdited(val description: String) : PlantDetailScreenEvent()
}
