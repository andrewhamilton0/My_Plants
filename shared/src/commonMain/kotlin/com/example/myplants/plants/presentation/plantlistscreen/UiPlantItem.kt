package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.core.presentation.util.DateDescriptor
import com.example.myplants.plants.domain.Photo
import com.example.myplants.plants.domain.PlantSize

data class UiPlantItem(
    val id: String,
    val nextWaterDateDescriptor: DateDescriptor,
    val photo: Photo?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val isWatered: Boolean,
    val plantSize: PlantSize
)
