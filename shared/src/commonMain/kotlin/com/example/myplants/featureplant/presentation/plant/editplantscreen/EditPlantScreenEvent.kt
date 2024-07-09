package com.example.myplants.featureplant.presentation.plant.editplantscreen

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
    data class UpdatePhoto(val byteArray: ByteArray) : EditPlantScreenEvent() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as UpdatePhoto

            return byteArray.contentEquals(other.byteArray)
        }

        override fun hashCode(): Int {
            return byteArray.contentHashCode()
        }
    }

    object SavePlant : EditPlantScreenEvent()
}
