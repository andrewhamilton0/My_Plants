package com.example.myplants.android.plant.presentation.plantdetailscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.myplants.android.plant.presentation.plantdetailscreen.components.PlantDetailScaffold
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenEvent
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlantDetailScreen(
    navController: NavController,
    plantId: String,
    logId: String,
    viewModel: PlantDetailScreenViewModel = getViewModel { parametersOf(plantId, logId) }
) {
    val plant = viewModel.state.collectAsState().value.plant

    PlantDetailScaffold(
        plant = plant,
        onBackButtonPress = { navController.popBackStack() },
        onEditPlantButtonPress = { navController.navigate(Screens.EditPlant(plantId)) },
        onToggleWaterButtonPress = { viewModel.onEvent(PlantDetailScreenEvent.ToggleWaterButton) }
    )
}
