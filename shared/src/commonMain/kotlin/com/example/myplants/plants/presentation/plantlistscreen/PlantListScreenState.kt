package com.example.myplants.plants.presentation.plantlistscreen

data class PlantListScreenState(
    val plants: List<UiPlantItem> = emptyList(),
    val selectedPlantListFilter: PlantListFilter = PlantListFilter.UPCOMING,
    val isNotificationBellNotifying: Boolean = false
)

enum class PlantListFilter {
    UPCOMING,
    FORGOT_TO_WATER,
    HISTORY
}
