package com.example.myplants.android.plant.presentation.plantdetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.plant.presentation.plantdetailscreen.components.scaffoldcomponents.DetailTopBarButtons
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.UiPlantDetailItem

@Composable
fun PlantDetailScaffold(
    plant: UiPlantDetailItem?,
    onBackButtonPress: () -> Unit,
    onEditPlantButtonPress: () -> Unit,
    onToggleWaterButtonPress: () -> Unit
) {
    @Composable
    fun PlantDetailScreen() {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutrals0)
        ) {
            val height = maxHeight
            val width = maxWidth
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height * 0.6f)
                    .align(Alignment.TopCenter)
            ) {
                val byteArray = plant?.photo?.byteArray
                if (byteArray == null) {
                    AsyncImage(
                        model = R.drawable.img_plants_background,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                } else {
                    AsyncImage(
                        model = byteArray,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Scaffold() { innerPadding ->
                Box(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .padding(innerPadding)
                ) {
                    DetailTopBarButtons(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = height * 0.015f)
                            .height(height * 0.05f)
                            .align(Alignment.TopCenter)
                    )
                }
            }

            /*
            // Bottom White Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp) // Adjust height as needed
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                ) {
                    // Plant Title
                    Text(
                        text = "Plant Title"
                    )

                    // Scrollable Description
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "This is the description text that can be really long and scrollable if needed...",
                            style = MaterialTheme.typography.body1
                        )
                    }

                    // Bottom Button
                    Button(
                        onClick = { /* Handle click */ },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("Bottom Button")
                    }
                }
            }

            // Floating Smaller White Box
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset(y = -50.dp) // Hovering effect
                    .align(Alignment.BottomCenter)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            ) {
                // Content of the smaller white box
            }

             */
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantDetailScaffoldPreview() {
    PlantDetailScaffold(
        plant = UiPlantDetailItem(
            plantId = "",
            waterAmount = "5ML",
            name = "Aguacate",
            description = "Deliciouss",
            plantSize = PlantSize.MEDIUM,
            isWatered = true,
            photo = null,
            waterFrequencyWeekly = 2
        ),
        onEditPlantButtonPress = {},
        onBackButtonPress = {},
        onToggleWaterButtonPress = {}
    )
}
