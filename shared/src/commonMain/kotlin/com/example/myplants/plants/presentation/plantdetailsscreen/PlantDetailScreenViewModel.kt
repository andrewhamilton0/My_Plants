package com.example.myplants.plants.presentation.plantdetailsscreen

import com.example.myplants.plants.domain.PlantRepository
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlantDetailScreenViewModel(
    private val plantRepository: PlantRepository,
    private val plantId: String
) : ViewModel() {

    private val _state = MutableStateFlow(PlantDetailScreenState())
    val state = _state.asStateFlow().cStateFlow()

    fun onEvent(event: PlantDetailScreenEvent) {
        when (event) {
            PlantDetailScreenEvent.ToggleWaterButton -> {
                viewModelScope.launch(NonCancellable) {
                    state.value.plant?.let { plant ->
                        plantRepository.upsertPlant(
                            plant = plant.copy(isWatered = !plant.isWatered)
                        )
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            plantRepository.getPlant(plantId).firstOrNull().also {
                it?.let { plant ->
                    _state.update { state ->
                        state.copy(plant = plant)
                    }
                }
            }
        }
    }
}
