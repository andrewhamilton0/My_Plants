package com.example.myplants.plants.presentation.plantdetailsscreen

import com.example.myplants.plants.domain.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlantDetailScreenViewModel(
    coroutineScope: CoroutineScope?,
    plantId: String
) : KoinComponent {

    private val plantRepository: PlantRepository by inject()
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(PlantDetailScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: PlantDetailScreenEvent) {
        when (event) {
            PlantDetailScreenEvent.ToggleWaterButton -> {
                viewModelScope.launch(NonCancellable) {
                    plantRepository.upsertPlant()
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            plantRepository.getPlant(plantId).firstOrNull().also {
                it?.let { plant ->
                    _state.update {state ->
                        state.copy(plant = plant)
                }
            }
        }
    }
}
