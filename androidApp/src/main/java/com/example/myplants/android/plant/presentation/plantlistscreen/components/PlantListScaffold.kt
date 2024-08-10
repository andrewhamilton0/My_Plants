package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.AddFab
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantListFilterBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantListScreenTopBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantsGrid
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListFilter
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantItem

@Composable
fun PlantListScaffold(
    isNotifying: Boolean,
    plants: List<UiPlantItem>,
    selectedPlantFilter: PlantListFilter,
    onFabClick: () -> Unit,
    onBellClick: () -> Unit,
    onPlantFilterClick: (PlantListFilter) -> Unit,
    onPlantCardClick: (String, String) -> Unit,
    onWaterButtonClick: (String) -> Unit
) {
    BoxWithConstraints() {
        val width = maxWidth
        val height = maxHeight
        val isScrollingDown = remember { mutableStateOf(false) }
        val fabVisibility = remember { mutableStateOf(plants.isNotEmpty() && isScrollingDown.value.not()) }
        println(fabVisibility.value.toString() + "fab")
        println(isScrollingDown.value.not().toString() + " is scrolling")

        Scaffold(
            floatingActionButton = {
                AnimatedVisibility(visible = fabVisibility.value) {
                    AddFab(
                        onClick = { onFabClick() },
                        modifier = Modifier
                            .testTag("add_button")
                            .size(height * 0.05f)
                    )
                }
            },
            backgroundColor = Neutrals0,
            isFloatingActionButtonDocked = true
        ) { innerPadding ->
            Box(
                Modifier
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .padding(innerPadding)
            ) {
                val screenHorizontalPadding = width * 0.07f
                Column(Modifier.padding(horizontal = screenHorizontalPadding)) {
                    Spacer(modifier = Modifier.height(height * 0.04f))
                    PlantListScreenTopBar(
                        isNotificationBellNotifying = isNotifying,
                        onNotificationBellClick = { onBellClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height * 0.08f)
                    )
                    Spacer(modifier = Modifier.height(height * 0.03f))
                    PlantListFilterBar(
                        onClick = { onPlantFilterClick(it) },
                        currentlySelected = selectedPlantFilter,
                        modifier = Modifier
                            .width(width * 0.71f)
                            .height(height * 0.03f)
                    )
                    Spacer(modifier = Modifier.height(height * 0.02f))
                    PlantsGrid(
                        plants = plants,
                        onCardClick = { plantId, logId ->
                            onPlantCardClick(plantId, logId)
                        },
                        onWaterButtonClick = { onWaterButtonClick(it) },
                        onScrollStateChange = { }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantListScaffoldPreview() {
    PlantListScaffold(
        isNotifying = true,
        plants = emptyList(),
        selectedPlantFilter = PlantListFilter.FORGOT_TO_WATER,
        onBellClick = { },
        onPlantFilterClick = { },
        onPlantCardClick = { _, _ -> },
        onWaterButtonClick = { },
        onFabClick = { }
    )
}
