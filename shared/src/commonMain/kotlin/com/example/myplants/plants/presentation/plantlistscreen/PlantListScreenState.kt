package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.plants.domain.Plant

data class PlantListScreenState(
    val plants: List<UiPlantItem> = emptyList(),
    val selectedPlantListFilter: PlantListFilter,
    val isNotificationBellNotifying: Boolean
)

enum class PlantListFilter {
    UPCOMING,
    FORGOT_TO_WATER,
    HISTORY
}
