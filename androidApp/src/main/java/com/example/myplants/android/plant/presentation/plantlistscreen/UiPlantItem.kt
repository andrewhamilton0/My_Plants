package com.example.myplants.android.plant.presentation.plantlistscreen

import androidx.compose.ui.graphics.vector.ImageVector

data class UiPlantItem(
    val nextWaterDate: String,
    val imageVector: ImageVector?,
    val waterAmount: String,
    val name: String,
    val description: String,
    val isWatered: Boolean
)
