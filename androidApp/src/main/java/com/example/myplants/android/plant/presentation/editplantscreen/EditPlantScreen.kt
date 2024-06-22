package com.example.myplants.android.plant.presentation.editplantscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myplants.plants.presentation.editplantscreen.EditPlantScreenEvent
import com.example.myplants.plants.presentation.editplantscreen.EditPlantScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditPlantScreen(
    navController: NavController,
    viewModel: EditPlantScreenViewModel = getViewModel()
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
                Button(onClick = {
                    viewModel.onEvent(EditPlantScreenEvent.SavePlant)
                    navController.popBackStack()
                }) {
                    Text(text = "Save plant")
                }
                TextField(
                    value = plant.name,
                    onValueChange = {
                        viewModel.onEvent(EditPlantScreenEvent.UpdateName(it))
                    },
                    label = { Text("Name") },
                    placeholder = { Text("Enter plant name") }
                )
                TextField(
                    value = plant.description,
                    onValueChange = {
                        viewModel.onEvent(EditPlantScreenEvent.UpdateDescription(it))
                    },
                    label = { Text("Description") },
                    placeholder = { Text("Enter plant description") }
                )
                TextField(
                    value = plant.waterAmount,
                    onValueChange = {
                        viewModel.onEvent(EditPlantScreenEvent.UpdateWaterAmount(it))
                    },
                    label = { Text("Water Amount") },
                    placeholder = { Text("Enter water amount") }
                )
            }
        }
    }
}
