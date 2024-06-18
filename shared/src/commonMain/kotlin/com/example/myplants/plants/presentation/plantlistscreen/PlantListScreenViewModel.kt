package com.example.myplants.plants.presentation.plantlistscreen

import com.example.myplants.plants.domain.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlantListScreenViewModel(
    coroutineScope: CoroutineScope?
) : KoinComponent {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val plantRepository: PlantRepository by inject()

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
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PlantListScreenState())

    fun onEvent(event: PlantListScreenEvent) {
        when (event) {
            is PlantListScreenEvent.AddPlant -> TODO()
            is PlantListScreenEvent.DeletePlant -> {
                viewModelScope.launch(NonCancellable) {
                    plantRepository.deletePlant(event.plantId)
                }
            }
            is PlantListScreenEvent.OpenNotificationScreen -> TODO()
            is PlantListScreenEvent.OpenPlant -> TODO()
            is PlantListScreenEvent.TogglePlantListFilter -> {
                _state.update { state ->
                    state.copy(selectedPlantListFilter = event.plantListFilter)
                }
            }
            is PlantListScreenEvent.WaterPlant -> {
                viewModelScope.launch(NonCancellable) {
                    val wateredPlant = plantRepository.getPlant(event.plantId)?.copy(
                        isWatered = true
                    )
                    wateredPlant?.let { plantRepository.upsertPlant(it) }
                }
            }
        }
    }
}
