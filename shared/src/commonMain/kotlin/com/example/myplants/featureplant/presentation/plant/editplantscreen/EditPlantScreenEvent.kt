package com.example.myplants.featureplant.presentation.plant.editplantscreen

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

sealed class EditPlantScreenEvent {
    data class UpdateName(val name: String) : EditPlantScreenEvent()
    data class AddWaterDay(val waterDay: DayOfWeek) : EditPlantScreenEvent()
    data class RemoveWaterDay(val waterDay: DayOfWeek) : EditPlantScreenEvent()
    data class UpdateTime(val time: LocalTime) : EditPlantScreenEvent()
    data class UpdateWaterAmount(val waterAmount: String) : EditPlantScreenEvent()
    data class UpdatePlantSize(val plantSize: PlantSize) : EditPlantScreenEvent()
    data class UpdateDescription(val description: String) : EditPlantScreenEvent()
    data class UpdatePhoto(val photo: Photo) : EditPlantScreenEvent()
    object SavePlant : EditPlantScreenEvent()
}
