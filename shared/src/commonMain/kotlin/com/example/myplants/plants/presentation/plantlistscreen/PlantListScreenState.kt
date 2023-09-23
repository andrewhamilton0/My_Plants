package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.plants.domain.Plant

data class PlantListScreenState(
    val plants: List<UiPlant> = emptyList(),
    val selectedPlantListFilter: PlantListFilter = PlantListFilter.UPCOMING
)

enum class PlantListFilter {
    UPCOMING,
    FORGOT_TO_WATER,
    HISTORY
}
