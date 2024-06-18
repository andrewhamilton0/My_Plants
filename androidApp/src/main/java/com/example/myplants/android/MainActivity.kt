package com.example.myplants.android

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myplants.android.plant.presentation.plantlistscreen.PlantListScreen
import com.example.myplants.core.presentation.util.DateDescriptor
import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantRepository
import com.example.myplants.plants.domain.PlantSize
import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenEvent
import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenState
import com.example.myplants.plants.presentation.plantlistscreen.UiPlantItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalTime
import org.koin.android.ext.android.inject
import java.time.LocalTime
import java.util.UUID

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.apply {
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }

        val plantRepository: PlantRepository by inject()

        var plant = SnapshotStateList<Plant>()
        plant.add(
            (
                Plant(
                    "",
                    "TEST",
                    "", "",
                    setOf(java.time.DayOfWeek.MONDAY),
                    LocalTime.now().toKotlinLocalTime(),
                    false,
                    PlantSize.MEDIUM,
                    null
                )
                )
        )
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantRepository.getPlants().collectLatest {
                    plant.clear()
                    plant.addAll(it)
                }
            }
        }
        setContent {
            MyApplicationTheme {
                PlantListScreen(
                    PlantListScreenState(
                        plants = remember {
                            listOf<UiPlantItem>(
                                UiPlantItem(
                                    id = "99",
                                    waterAmount = "500ml",
                                    name = "Arbol",
                                    description = "Small tree",
                                    isWatered = true,
                                    nextWaterDateDescriptor = DateDescriptor.Today,
                                    plantSize = PlantSize.MEDIUM,
                                    photo = null
                                ),
                                UiPlantItem(
                                    id = "99",
                                    waterAmount = "500ml",
                                    name = "Arbol",
                                    description = "Small tree",
                                    isWatered = true,
                                    nextWaterDateDescriptor = DateDescriptor.Today,
                                    plantSize = PlantSize.MEDIUM,
                                    photo = null
                                ),
                                UiPlantItem(
                                    id = "99",
                                    waterAmount = "500ml",
                                    name = "Arbol",
                                    description = "Small tree",
                                    isWatered = true,
                                    nextWaterDateDescriptor = DateDescriptor.Today,
                                    plantSize = PlantSize.MEDIUM,
                                    photo = null
                                ),
                                UiPlantItem(
                                    id = "99",
                                    waterAmount = "500ml",
                                    name = "Arbol",
                                    description = "Small tree",
                                    isWatered = true,
                                    nextWaterDateDescriptor = DateDescriptor.Tomorrow,
                                    plantSize = PlantSize.MEDIUM,
                                    photo = null
                                ),
                                UiPlantItem(
                                    id = "99",
                                    waterAmount = "500ml",
                                    name = "Arbol",
                                    description = "Small tree",
                                    isWatered = true,
                                    nextWaterDateDescriptor = DateDescriptor.Date(LocalDate(2024, 1, 22)),
                                    plantSize = PlantSize.MEDIUM,
                                    photo = null
                                )
                            )
                        },
                        isNotificationBellNotifying = true
                    ),
                    onEvent = { event ->
                        when (event) {
                            is PlantListScreenEvent.AddPlant -> TODO()
                            is PlantListScreenEvent.DeletePlant -> TODO()
                            is PlantListScreenEvent.OpenNotificationScreen -> TODO()
                            is PlantListScreenEvent.OpenPlant -> TODO()
                            is PlantListScreenEvent.TogglePlantListFilter -> TODO()
                            is PlantListScreenEvent.WaterPlant -> TODO()
                        }
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantName(onClick: (Plant) -> Unit) {
    var text by remember { mutableStateOf("Please enter plant name") }
    Row() {
        TextField(value = text, onValueChange = { text = it })
        TextButton(onClick = {
            val newPlant = Plant(
                id = UUID.randomUUID().toString(),
                description = "",
                waterAmount = "3L",
                waterDays = setOf<DayOfWeek>(DayOfWeek.MONDAY),
                isWatered = false,
                name = text,
                waterTime = java.time.LocalTime.now().toKotlinLocalTime(),
                photo = null,
                plantSize = PlantSize.MEDIUM
            )
            onClick(newPlant)
            text = "Please enter plant name"
        }) {
        }
    }
}

@Composable
fun CurrentPlantsSaved(plants: List<Plant>) {
    Column {
        plants.forEach { plant ->
            Text(plant.name)
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
