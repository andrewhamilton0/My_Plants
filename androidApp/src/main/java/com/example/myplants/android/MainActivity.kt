package com.example.myplants.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.myplants.Greeting
import com.example.myplants.plants.domain.Plant
import com.example.myplants.plants.domain.PlantRepository
import com.example.myplants.plants.domain.PlantSize
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.toKotlinLocalTime
import org.koin.android.ext.android.inject
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val plantRepository: PlantRepository by inject()

        var plantsList = emptyList<Plant>()
        /*
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantRepository.getPlants().collectLatest {
                    plantsList = it
                }
            }
        }
         */
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                    PlantName(onClick = { plant ->
                        lifecycleScope.launch {
                            plantRepository.upsertPlant(plant)
                        }
                    })
                    CurrentPlantsSaved(plants = plantsList)
                }
            }
        }
    }
}

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
                photoKey = null,
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
            Text(plant.name + "1")
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
