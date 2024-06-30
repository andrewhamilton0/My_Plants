package com.example.myplants.featureplant.presentation.plant.plantlistscreen

import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.WaterStatus
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.toUiPlant
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PlantListScreenViewModel(
    private val plantManagementService: PlantManagementService
) : ViewModel() {

    private val upcomingPlants = plantManagementService.getUpcomingPlants()
    private val forgottenPlants = plantManagementService.getForgottenPlants()
    private val plantHistory = plantManagementService.getHistory()

    private val _state = MutableStateFlow(PlantListScreenState())
    val state = combine(_state, upcomingPlants, forgottenPlants, plantHistory)
    { state, upcomingPlants, forgottenPlants, plantHistory ->
        val filteredPlants = when (state.selectedPlantListFilter) {
            PlantListFilter.UPCOMING -> {
                state.
            }
            PlantListFilter.FORGOT_TO_WATER -> {
                plantList.filter { it.waterStatus is WaterStatus.Forgotten }.sortedBy {
                    it.waterStatus.localDateTime
                }
            }
            PlantListFilter.ALL -> plantList
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
                    plantRepository.deletePlant(event.plantId)
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

                }
            }
        }
    }
}
