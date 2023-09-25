package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.plants.domain.Photo

data class UiPlantItem(
    val id: String,
    val nextWaterDate: String,
    val photo: Photo?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val isWatered: Boolean
)
