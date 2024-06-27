package com.example.myplants.featureplant.presentation.plant.editplantscreen

import com.example.myplants.core.domain.DateUtil
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.plant.PlantSize
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class EditPlantScreenViewModel(
    private val repository: PlantRepository,
    private val plantId: String?
) : ViewModel() {

    private val _state = MutableStateFlow(
        EditPlantScreenState(
            plant = Plant(
                name = "",
                description = "",
                waterAmount = "",
                waterDays = emptySet(),
                waterTime = LocalTime(12, 0),
                isWatered = false,
                plantSize = PlantSize.SMALL,
                photo = null,
                creationDate = DateUtil.getCurrentDate(),
                waterHistory = emptySet()
            )
        )
    )
    val state = _state.asStateFlow().cStateFlow()

    fun onEvent(event: EditPlantScreenEvent) {
        when (event) {
            is EditPlantScreenEvent.UpdateDescription -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            description = event.description
                        )
                    )
                }
            }
            is EditPlantScreenEvent.UpdateName -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            name = event.name
                        )
                    )
                }
            }
            is EditPlantScreenEvent.UpdatePlantSize -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            plantSize = event.plantSize
                        )
                    )
                }
            }
            is EditPlantScreenEvent.UpdateTime -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            waterTime = event.time
                        )
                    )
                }
            }
            is EditPlantScreenEvent.UpdateWaterAmount -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            waterAmount = event.waterAmount
                        )
                    )
                }
            }
            is EditPlantScreenEvent.SavePlant -> {
                if (state.value.plant.waterDays.isEmpty()) {
                    // TODO: Tell user to add at least one water day
                } else {
                    viewModelScope.launch(NonCancellable) {
                        repository.savePlant(state.value.plant)
                    }
                }
            }
            is EditPlantScreenEvent.AddWaterDay -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            waterDays = state.plant.waterDays.plus(event.waterDay)
                        )
                    )
                }
            }
            is EditPlantScreenEvent.RemoveWaterDay -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            waterDays = state.plant.waterDays.minus(event.waterDay)
                        )
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            val plant = plantId?.let { repository.getPlant(it).first() }
            plant?.let {
                _state.update { state ->
                    state.copy(
                        plant = plant
                    )
                }
            }
        }
    }
}
