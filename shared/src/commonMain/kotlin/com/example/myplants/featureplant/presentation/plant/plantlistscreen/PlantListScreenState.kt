package com.example.myplants.featureplant.presentation.plant.plantlistscreen

data class PlantListScreenState(
    val plants: List<UiPlantListItem> = emptyList(),
    val plantDbIsEmpty: Boolean = true,
    val selectedPlantListFilter: PlantListFilter = PlantListFilter.UPCOMING,
    val isNotificationBellNotifying: Boolean = false
)

enum class PlantListFilter {
    UPCOMING,
    FORGOT_TO_WATER,
    HISTORY
}
