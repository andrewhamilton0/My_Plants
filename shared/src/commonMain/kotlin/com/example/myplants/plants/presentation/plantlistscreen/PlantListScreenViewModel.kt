package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.plants.domain.PlantRepository
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PlantListScreenViewModel(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val currentDateTime = flow<LocalDateTime> {
        while (true) {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            delay(60 * 1000L)
        }
    }

    private val plants = combine(plantRepository.getPlants(), currentDateTime) { plants, dateTime ->
        plants.map { it.toUiPlant(dateTime.date) }
    }
    private val _state = MutableStateFlow(PlantListScreenState())
    val state = combine(_state, plants) { state, plantList ->
        if (plantList != state.plants) {
            state.copy(plants = plantList)
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
            }
            is PlantListScreenEvent.ToggleWater -> {
                viewModelScope.launch(NonCancellable) {
                    plantRepository.getPlant(event.plantId).firstOrNull().also {
                        it?.let { plant ->
                            plantRepository.upsertPlant(plant.copy(isWatered = !plant.isWatered))
                        }
                    }
                }
            }
        }
    }
}
