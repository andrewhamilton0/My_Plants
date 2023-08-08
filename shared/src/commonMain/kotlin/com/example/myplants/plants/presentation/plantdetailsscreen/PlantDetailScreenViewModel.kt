package com.example.myplants.plants.presentation.plantdetailsscreen

import com.example.myplants.plants.domain.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlantDetailScreenViewModel(
    coroutineScope: CoroutineScope?
) : KoinComponent {

    private val plantRepository: PlantRepository by inject()
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(PlantDetailScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: PlantDetailScreenEvent) {
        when (event) {
            is PlantDetailScreenEvent.BackButtonPressed -> TODO()
            is PlantDetailScreenEvent.ChangeImageButtonPressed -> TODO()
            is PlantDetailScreenEvent.EditButtonPressed -> TODO()
            is PlantDetailScreenEvent.SavePlant -> {
                viewModelScope.launch(NonCancellable) {
                    plantRepository.savePlant(event.plant)
                }
                TODO("EXIT PLANT DETAIL SCREEN")
            }
            is PlantDetailScreenEvent.WaterDaysEdited -> {
                _state.update { state ->
                    state.copy(waterDays = event.waterDays)
                }
            }
            is PlantDetailScreenEvent.DescriptionEdited -> {
                _state.update { state ->
                    state.copy(description = event.description)
                }
            }
            is PlantDetailScreenEvent.NameEdited -> {
                _state.update { state ->
                    state.copy(name = event.name)
                }
            }
            is PlantDetailScreenEvent.PlantSizeEdited -> {
                _state.update { state ->
                    state.copy(plantSize = event.plantSize)
                }
            }
            is PlantDetailScreenEvent.TimeEdited -> {
                _state.update { state ->
                    state.copy(waterTime = event.time)
                }
            }
            is PlantDetailScreenEvent.ToggleWaterButton -> {
                _state.update { state ->
                    state.copy(isWatered = !state.isWatered)
                }
            }
            is PlantDetailScreenEvent.WaterAmountEdited -> {
                _state.update { state ->
                    state.copy(waterAmount = event.waterAmount)
                }
            }
        }
    }
}
