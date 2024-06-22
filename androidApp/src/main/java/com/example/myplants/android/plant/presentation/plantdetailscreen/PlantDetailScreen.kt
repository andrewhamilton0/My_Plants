package com.example.myplants.android.plant.presentation.plantdetailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.plants.presentation.plantdetailsscreen.PlantDetailScreenEvent
import com.example.myplants.plants.presentation.plantdetailsscreen.PlantDetailScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantDetailScreen(
    navController: NavController,
    viewModel: PlantDetailScreenViewModel = getViewModel()
) {
    val plant = viewModel.state.value.plant

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Text(text = "Second Page :D")
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Go to previous page")
                }
                Button(onClick = { navController.navigate(Screens.EditPlant(plant?.id)) }) {
                    Text(text = "Edit")
                }
                Button(onClick = { viewModel.onEvent(PlantDetailScreenEvent.ToggleWaterButton) }) {
                    Text(text = "Water Plant")
                }
                Text(text = "Name: ${plant?.name}")
                Text(text = "Description: ${plant?.description}")
                Text(text = "Is watered: ${plant?.isWatered}")
                Text(text = "Water amount: ${plant?.waterAmount}")
            }
        }
    }
}