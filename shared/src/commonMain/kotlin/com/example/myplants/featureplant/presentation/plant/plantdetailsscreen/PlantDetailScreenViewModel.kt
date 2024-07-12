package com.example.myplants.featureplant.presentation.plant.plantdetailsscreen

import com.example.myplants.core.domain.util.DateUtil
import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.util.toUiPlant
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlantDetailScreenViewModel(
    private val plantManagementService: PlantManagementService,
    private val plantId: String,
    private val waterLogId: String
) : ViewModel() {

    // TODO CHANGE plant repo to service
    private val currentDate = DateUtil.getCurrentDateTime().map { it.date }
    private val plant = combine(plantManagementService.getPlantWaterLogPair(plantId, waterLogId), currentDate) { plant, date ->
        plant?.toUiPlant(date)
    }

    private val _state = MutableStateFlow(PlantDetailScreenState(null))
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
                    plant?.logId?.let { plantManagementService.toggleWater(it) }
                }
            }
        }
    }
}
