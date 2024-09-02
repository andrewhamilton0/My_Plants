package com.example.myplants.featureplant.presentation.plant.plantlistscreen

import com.example.myplants.core.domain.util.DateUtil
import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.toUiPlantListItem
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

    private val _state = MutableStateFlow(PlantListScreenState())
    val state = combine(_state, upcomingPlants, forgottenPlants, plantHistory, currentDateTime) { state, upcomingPlants, forgottenPlants, plantHistory, currentDateTime ->
        val currentDate = currentDateTime.date
        val filteredPlants = when (state.selectedPlantListFilter) {
            PlantListFilter.UPCOMING -> {
                upcomingPlants.map { it.toUiPlantListItem(currentDate) }
            }
            PlantListFilter.FORGOT_TO_WATER -> {
                forgottenPlants.map { it.toUiPlantListItem(currentDate) }
            }
            PlantListFilter.HISTORY -> {
                plantHistory.map { it.toUiPlantListItem(currentDate) }
            }
        }
        val plantDbIsEmpty = upcomingPlants.isEmpty()
        if (filteredPlants != state.plants || plantDbIsEmpty != state.plantDbIsEmpty) {
            state.copy(plants = filteredPlants, plantDbIsEmpty = plantDbIsEmpty)
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
            }
            is PlantListScreenEvent.ToggleWater -> {
                viewModelScope.launch(NonCancellable) {
                    plantManagementService.toggleWater(event.waterLogId)
                }
            }
        }
    }
}
