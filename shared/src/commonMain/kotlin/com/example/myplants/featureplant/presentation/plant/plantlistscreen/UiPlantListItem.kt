package com.example.myplants.featureplant.presentation.plant.plantlistscreen

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor

data class UiPlantListItem(
    val plantId: String,
    val photo: Photo?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val plantSize: PlantSize,
    val dateDescriptor: DateDescriptor,
    val isWatered: Boolean,
    val logId: String
)
