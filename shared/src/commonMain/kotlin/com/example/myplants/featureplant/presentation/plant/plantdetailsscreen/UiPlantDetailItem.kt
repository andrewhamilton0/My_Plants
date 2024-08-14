package com.example.myplants.featureplant.presentation.plant.plantdetailsscreen

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize

data class UiPlantDetailItem(
    val plantId: String,
    val photo: Photo?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val plantSize: PlantSize,
    val isWatered: Boolean,
    val waterFrequencyWeekly: Int
)
