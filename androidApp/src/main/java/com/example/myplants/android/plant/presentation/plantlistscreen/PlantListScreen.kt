package com.example.myplants.android.plant.presentation.plantlistscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListScaffold
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListFilter
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenEvent
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PlantListScreen(
    viewModel: PlantListScreenViewModel = getViewModel(),
    navController: NavController
) {
    val plants by viewModel.state.mapLatest { it.plants }.collectAsState(initial = emptyList())
    val isNotificationBellNotifying by viewModel.state.mapLatest { it.isNotificationBellNotifying }.collectAsState(initial = false)
    val selectedFilter by viewModel.state.mapLatest { it.selectedPlantListFilter }.collectAsState(initial = PlantListFilter.UPCOMING)
    val plantDbIsEmpty by viewModel.state.mapLatest { it.plantDbIsEmpty }.collectAsState(initial = true)

    PlantListScaffold(
        isNotifying = isNotificationBellNotifying,
        plants = plants,
        plantDbIsEmpty = plantDbIsEmpty,
        selectedPlantFilter = selectedFilter,
        onAddPlantClick = { navController.navigate(Screens.EditPlant()) },
        onBellClick = { navController.navigate(Screens.Notification) },
        onPlantFilterClick = { viewModel.onEvent(PlantListScreenEvent.TogglePlantListFilter(it)) },
        onPlantCardClick = { plantId, logId ->
            navController.navigate(Screens.PlantDetail(plantId, logId))
        },
        onWaterButtonClick = { viewModel.onEvent(PlantListScreenEvent.ToggleWater(it)) }
    )
}
