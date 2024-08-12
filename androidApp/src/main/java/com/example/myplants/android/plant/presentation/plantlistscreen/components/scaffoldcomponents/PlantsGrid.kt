package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantItem

@Composable
fun PlantsGrid(
    plants: List<UiPlantItem>,
    onCardClick: (String, String) -> Unit,
    onWaterButtonClick: (String) -> Unit,
    onIsScrollingDownStateChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val screenWidth = maxWidth
        val itemPadding = screenWidth * 0.05f

        val cellSize = when {
            screenWidth < 600.dp -> 167.dp
            screenWidth < 840.dp -> 200.dp
            else -> 240.dp
        }

        val lazyGridState = rememberLazyGridState()
        val isScrollingDown = remember {
            derivedStateOf {
                lazyGridState.lastScrolledForward
            }
        }
        onIsScrollingDownStateChange(isScrollingDown.value)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(cellSize),
            state = lazyGridState,
            modifier = Modifier
                .fillMaxSize()
                .testTag("plant_list"),
            horizontalArrangement = Arrangement.spacedBy(itemPadding),
            verticalArrangement = Arrangement.spacedBy(itemPadding)
        ) {
            items(plants, key = { it.logId }) { _plant ->
                val plant by rememberUpdatedState(_plant)
                PlantItemHolder(
                    plant = plant,
                    onCardClick = { onCardClick(plant.plantId, plant.logId) },
                    onWaterButtonClick = { onWaterButtonClick(plant.logId) }
                )
            }
        }
    }
}
