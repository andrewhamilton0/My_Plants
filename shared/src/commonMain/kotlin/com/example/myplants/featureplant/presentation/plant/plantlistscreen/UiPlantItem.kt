package com.example.myplants.featureplant.presentation.plant.plantlistscreen

import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.WaterStatus
import kotlinx.datetime.LocalDate

data class UiPlantItem(
    val id: String,
    val photo: Photo?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val isWatered: Boolean,
    val plantSize: PlantSize,
    val waterStatus: WaterStatus,
    val waterHistory: Set<LocalDate>
)
