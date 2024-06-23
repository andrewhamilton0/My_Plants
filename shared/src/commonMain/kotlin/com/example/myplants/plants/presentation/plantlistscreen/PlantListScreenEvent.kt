package com.example.myplants.plants.presentation.plantlistscreen

sealed class PlantListScreenEvent {
    data class TogglePlantListFilter(val plantListFilter: PlantListFilter) : PlantListScreenEvent()
    data class ToggleWater(val plantId: String) : PlantListScreenEvent()
    data class DeletePlant(val plantId: String) : PlantListScreenEvent()
}
