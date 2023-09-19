package com.example.myplants.android.plant.presentation.plantlistscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.plant.presentation.plantlistscreen.components.AddFab
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantItemHolder
import com.example.myplants.android.plant.presentation.plantlistscreen.components.PlantListScreenTopBar

@Composable
fun PlantListScreen(
    plantList: List<UiPlantItem>,
    isNotificationBellNotifying: Boolean,
    onNotificationBellClick: () -> Unit
) {
    Scaffold(
        topBar = {
            PlantListScreenTopBar(
                isNotificationBellNotifying = isNotificationBellNotifying,
                onNotificationBellClick = onNotificationBellClick
            )
        },
        floatingActionButton = { AddFab(onClick = { /*TODO*/ }) },
        backgroundColor = Neutrals0
    ) { innerPadding ->

        LazyVerticalGrid(columns = , content = )

        LazyColumn(contentPadding = innerPadding) {
            items(plantList) { plant ->
                PlantItemHolder(plant = plant)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPlantListScreen() {
    PlantListScreen(
        plantList = remember {
            listOf<UiPlantItem>(
                UiPlantItem(
                    nextWaterDate = "Today",
                    imageVector = null,
                    waterAmount = "500ml",
                    name = "Arbol",
                    description = "Small tree",
                    isWatered = true
                ),
                UiPlantItem(
                    nextWaterDate = "Today",
                    imageVector = null,
                    waterAmount = "500ml",
                    name = "Arbol",
                    description = "Small tree",
                    isWatered = true
                ),
                UiPlantItem(
                    nextWaterDate = "Tomorrow",
                    imageVector = null,
                    waterAmount = "50ml",
                    name = "Mostero",
                    description = "Some Description",
                    isWatered = false
                ),
                UiPlantItem(
                    nextWaterDate = "Tomorrow",
                    imageVector = null,
                    waterAmount = "50ml",
                    name = "Mostero",
                    description = "Some Description",
                    isWatered = false
                )
            )
        },
        onNotificationBellClick = { Unit },
        isNotificationBellNotifying = true
    )
}
