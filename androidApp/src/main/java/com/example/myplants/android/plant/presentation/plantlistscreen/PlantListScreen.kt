package com.example.myplants.android.plant.presentation.plantlistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.plant.presentation.plantlistscreen.components.AddFab
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantItemHolder
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListFilterBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListScreenTopBar
import com.example.myplants.android.plant.presentation.util.Screens
import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenEvent
import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenViewModel
import org.koin.androidx.compose.getViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantListScreen(
    viewModel: PlantListScreenViewModel = getViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            AddFab(
                onClick = {
                    navController.navigate(Screens.PlantDetail())
                }
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
                    isNotificationBellNotifying = state.isNotificationBellNotifying,
                    onNotificationBellClick = {
                        navController.navigate(Screens.Notification)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                PlantListFilterBar(
                    onClick = { viewModel.onEvent(PlantListScreenEvent.TogglePlantListFilter(it)) },
                    currentlySelected = state.selectedPlantListFilter
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.plants) { plant ->
                        PlantItemHolder(
                            plant = plant,
                            onCardClick = {
                                navController.navigate(Screens.PlantDetail(plant.id))
                            },
                            onWaterButtonClick = {
                                viewModel.onEvent(PlantListScreenEvent.WaterPlant(plant.id))
                            }
                        )
                    }
                }
            }
        }
    }
}
