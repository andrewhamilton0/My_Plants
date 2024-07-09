package com.example.myplants.featureplant.presentation.plant.plantlistscreen

sealed class PlantListScreenEvent {
    data class TogglePlantListFilter(val plantListFilter: PlantListFilter) : PlantListScreenEvent()
    data class ToggleWater(val waterLogId: String) : PlantListScreenEvent()
    data class DeletePlant(val plantId: String, val photoKey: String?) : PlantListScreenEvent()
}
