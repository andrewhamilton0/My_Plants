package com.example.myplants.featureplant.presentation.plant.plantdetailsscreen

import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.plant.PlantSize
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

class PlantDetailScreenViewModel(
    private val plantRepository: PlantRepository,
    private val plantId: String
) : ViewModel() {

    private val plant = plantRepository.getPlant(plantId)

    private val _state = MutableStateFlow(
        PlantDetailScreenState(
            plant = Plant(
                name = "",
                description = "",
                waterAmount = "",
                waterDays = emptySet(),
                waterTime = LocalTime(12, 0),
                isWatered = false,
                plantSize = PlantSize.SMALL,
                photo = null
            )
        )
    )
    val state = combine(_state, plant) { state, plant ->
        if (state.plant != plant && plant != null) {
            state.copy(plant = plant)
        } else {
            state
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _state.value
    ).cStateFlow()

    fun onEvent(event: PlantDetailScreenEvent) {
        when (event) {
            PlantDetailScreenEvent.ToggleWaterButton -> {
                viewModelScope.launch(NonCancellable) {
                    val plant = state.value.plant
                    plantRepository.upsertPlant(plant.copy(isWatered = !plant.isWatered))
                }
            }
        }
    }
}
