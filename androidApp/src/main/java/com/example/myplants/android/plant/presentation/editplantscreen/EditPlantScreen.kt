package com.example.myplants.android.plant.presentation.editplantscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.myplants.android.core.presentation.util.rememberPhotoPickerLauncher
import com.example.myplants.android.plant.presentation.editplantscreen.components.EditPlantScaffold
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenEvent
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditPlantScreen(
    navController: NavController,
    plantId: String?,
    viewModel: EditPlantScreenViewModel = getViewModel { parametersOf(plantId) }
) {
    val plant = viewModel.state.collectAsState().value.plant
    val allFieldsRequiredFilled = viewModel.state.collectAsState().value.allFieldsRequiredFilled
    val photoPickerLauncher = rememberPhotoPickerLauncher { photo ->
        photo?.let { viewModel.onEvent(EditPlantScreenEvent.UpdatePhoto(it)) }
    }

    EditPlantScaffold(
        plant = plant,
        allRequiredFieldsFilled = allFieldsRequiredFilled,
        onTitleChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateName(it)) },
        onDescriptionChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateDescription(it)) },
        onSaveClick = {
            viewModel.onEvent(EditPlantScreenEvent.SavePlant)
            navController.popBackStack(Screens.PlantList, false)
        },
        onWaterAmountChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateWaterAmount(it)) },
        onPhotoButtonClick = { photoPickerLauncher() },
        onBackClick = { navController.popBackStack() },
        onPlantSizeChange = { viewModel.onEvent(EditPlantScreenEvent.UpdatePlantSize(it)) },
        onDateChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateWaterDays(it)) },
        onTimeChange = { viewModel.onEvent(EditPlantScreenEvent.UpdateTime(it)) }
    )
}
