package com.example.myplants.android.plant.presentation.editplantscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.myplants.android.core.presentation.util.rememberPhotoPickerLauncher
import com.example.myplants.android.plant.presentation.editplantscreen.components.EditPlantScaffold
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenEvent
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditPlantScreen(
    navController: NavController,
    plantId: String?,
    viewModel: EditPlantScreenViewModel = getViewModel { parametersOf(plantId) }
) {
    val plant = viewModel.state.collectAsState().value.plant
    val photoPickerLauncher = rememberPhotoPickerLauncher { photo ->
        photo?.let { viewModel.onEvent(EditPlantScreenEvent.UpdatePhoto(it)) }
    }

    EditPlantScaffold(
        plant = plant,
        onTitleChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateName(it)) },
        onDescriptionChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateDescription(it)) },
        onSaveClick = {
            viewModel.onEvent(EditPlantScreenEvent.SavePlant)
            navController.popBackStack()
        },
        onWaterAmountChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateWaterAmount(it)) },
        onPhotoButtonClick = { photoPickerLauncher() },
        onBackClick = { navController.popBackStack() }
    )
    /*
    @Composable
    fun DayOfWeekSelector(dayOfWeek: DayOfWeek) {
        Row() {
            Text(text = dayOfWeek.name.uppercase())
            Checkbox(
                checked = plant.waterDays.contains(dayOfWeek),
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        viewModel.onEvent(EditPlantScreenEvent.AddWaterDay(dayOfWeek))
                    } else {
                        viewModel.onEvent(EditPlantScreenEvent.RemoveWaterDay(dayOfWeek))
                    }
                }
            )
        }
    }

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
                val photoPickerLauncher = rememberPhotoPickerLauncher { photo ->
                    photo?.let { viewModel.onEvent(EditPlantScreenEvent.UpdatePhoto(it)) }
                }

                Button(
                    onClick = { photoPickerLauncher() },
                    content = { Text(text = "Select Photo") }
                )
                Button(
                    onClick = { viewModel.onEvent(EditPlantScreenEvent.UpdateTime(LocalTime(14, 16))) },
                    content = { Text(text = "Change Time ${plant.waterTime}") }
                )
                DayOfWeekSelector(DayOfWeek.MONDAY)
                DayOfWeekSelector(DayOfWeek.TUESDAY)
                DayOfWeekSelector(DayOfWeek.WEDNESDAY)
                DayOfWeekSelector(DayOfWeek.THURSDAY)
                DayOfWeekSelector(DayOfWeek.FRIDAY)
                DayOfWeekSelector(DayOfWeek.SATURDAY)
                DayOfWeekSelector(DayOfWeek.SUNDAY)
            }
        }
    }

     */
}
