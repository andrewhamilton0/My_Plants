package com.example.myplants.android.plant.presentation.plantlistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.plant.presentation.plantlistscreen.components.AddFab
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListFilterBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListScreenTopBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantsGrid
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListFilter
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenEvent
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantListScreen(
    viewModel: PlantListScreenViewModel = getViewModel(),
    navController: NavController
) {
    val plants by viewModel.state.mapLatest { it.plants }.collectAsState(initial = emptyList())
    val isNotificationBellNotifying by viewModel.state.mapLatest { it.isNotificationBellNotifying }.collectAsState(initial = false)
    val selectedFilter by viewModel.state.mapLatest { it.selectedPlantListFilter }.collectAsState(initial = PlantListFilter.UPCOMING)

    Scaffold(
        floatingActionButton = {
            AddFab(
                onClick = {
                    navController.navigate(Screens.EditPlant())
                },
                modifier = Modifier.testTag("add_button")
            )
        },
        backgroundColor = Neutrals0
    ) { innerPadding ->
        Box(
            Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(innerPadding)
        ) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(32.dp))
                PlantListScreenTopBar(
                    isNotificationBellNotifying = isNotificationBellNotifying,
                    onNotificationBellClick = {
                        navController.navigate(Screens.Notification)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                PlantListFilterBar(
                    onClick = { viewModel.onEvent(PlantListScreenEvent.TogglePlantListFilter(it)) },
                    currentlySelected = selectedFilter
                )
                Spacer(modifier = Modifier.height(20.dp))
                PlantsGrid(
                    plants = plants,
                    onCardClick = { plantId, logId ->
                        navController.navigate(Screens.PlantDetail(plantId, logId))
                    },
                    onWaterButtonClick = { logId ->
                        viewModel.onEvent(PlantListScreenEvent.ToggleWater(logId))
                    }
                )
            }
        }
    }
}
