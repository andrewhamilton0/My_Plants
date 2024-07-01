package com.example.myplants.featureplant.presentation.plant.plantlistscreen

import com.example.myplants.core.domain.DateUtil
import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.toUiPlant
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlantListScreenViewModel(
    private val plantManagementService: PlantManagementService
) : ViewModel() {

    private val upcomingPlants = plantManagementService.getUpcomingPlants()
    private val forgottenPlants = plantManagementService.getForgottenPlants()
    private val plantHistory = plantManagementService.getHistory()
    private val currentDateTime = DateUtil.getCurrentDateTime()

    // TODO FIX DATES SHOWN
    private val _state = MutableStateFlow(PlantListScreenState())
    val state = combine(_state, upcomingPlants, forgottenPlants, plantHistory, currentDateTime) { state, upcomingPlants, forgottenPlants, plantHistory, currentDateTime ->
        val currentDate = currentDateTime.date
        val filteredPlants = when (state.selectedPlantListFilter) {
            PlantListFilter.UPCOMING -> {
                upcomingPlants.map { it.toUiPlant(currentDate) }
            }
            PlantListFilter.FORGOT_TO_WATER -> {
                forgottenPlants.map { it.toUiPlant(currentDate) }
            }
            PlantListFilter.HISTORY -> {
                plantHistory.map { it.toUiPlant(currentDate) }
            }
        }
        if (filteredPlants != state.plants) {
            state.copy(plants = filteredPlants)
        } else {
            state
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        PlantListScreenState()
    ).cStateFlow()

    fun onEvent(event: PlantListScreenEvent) {
        when (event) {
            is PlantListScreenEvent.DeletePlant -> {
                viewModelScope.launch(NonCancellable) {
                    plantManagementService.deletePlant(event.plantId)
                }
            }
            is PlantListScreenEvent.TogglePlantListFilter -> {
                _state.update { state ->
                    state.copy(selectedPlantListFilter = event.plantListFilter)
                }
                println(event.plantListFilter.name)
            }
            is PlantListScreenEvent.ToggleWater -> {
                viewModelScope.launch(NonCancellable) {
                    plantManagementService.toggleWater(event.waterLogId)
                }
            }
        }
    }
}
