package com.example.myplants.plants.presentation.plantlistscreen

sealed class PlantListScreenEvent {
    data class TogglePlantListFilter(val plantListFilter: PlantListFilter) : PlantListScreenEvent()
    data class WaterPlant(val plantId: String) : PlantListScreenEvent()
    data class DeletePlant(val plantId: String) : PlantListScreenEvent()
}
