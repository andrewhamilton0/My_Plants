package com.example.myplants.featureplant.presentation.plant.editplantscreen

import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.domain.plant.Photo
import com.example.myplants.featureplant.domain.plant.PlantSize
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class EditPlantScreenViewModel(
    private val plantManagementService: PlantManagementService,
    private val plantId: String?,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(
        EditPlantScreenState(
            plant = UiEditPlantItem(
                id = null,
                name = "",
                description = "",
                waterAmount = "",
                waterDays = emptySet(),
                waterTime = LocalTime(12, 0),
                plantSize = PlantSize.SMALL,
                photo = null
            ),
            allFieldsRequiredFilled = false
        )
    )
    val state = _state.mapLatest { state ->
        state.copy(allFieldsRequiredFilled = areAllFieldsRequiredFilled(state.plant))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

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
                viewModelScope.launch(dispatcherIO + NonCancellable) {
                    plantManagementService.upsertPlant(state.value.plant.toPlant())
                }
            }

            is EditPlantScreenEvent.UpdatePhoto -> {
                _state.update { state ->
                    if (state.plant.photo == null) {
                        state.copy(
                            plant = state.plant.copy(
                                photo = Photo(byteArray = event.byteArray)
                            )
                        )
                    } else {
                        state.copy(
                            plant = state.plant.copy(
                                photo = state.plant.photo.copy(
                                    byteArray = event.byteArray
                                )
                            )
                        )
                    }
                }
            }

            is EditPlantScreenEvent.UpdateWaterDays -> {
                _state.update { state ->
                    state.copy(
                        plant = state.plant.copy(
                            waterDays = event.waterDays
                        )
                    )
                }
            }
        }
    }

    private fun areAllFieldsRequiredFilled(plant: UiEditPlantItem): Boolean {
        return if (plant.name.length < 1) {
            false
        } else if (plant.waterDays.isEmpty()) {
            false
        } else if (plant.description.length < 1) {
            false
        } else if (plant.waterAmount.length < 1) {
            false
        } else {
            true
        }
    }

    init {
        viewModelScope.launch {
            val plant = plantId?.let { plantManagementService.getPlant(it).first() }
            plant?.let {
                _state.update { state ->
                    state.copy(
                        plant = plant.toUiEditPlant()
                    )
                }
            }
        }
    }
}
