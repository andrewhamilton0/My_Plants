package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.AddFab
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantListFilterBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantListScreenTopBar
import com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.PlantsGrid
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListFilter
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantListItem

@Composable
fun PlantListScaffold(
    isNotifying: Boolean,
    plants: List<UiPlantListItem>,
    plantDbIsEmpty: Boolean,
    selectedPlantFilter: PlantListFilter,
    onAddPlantClick: () -> Unit,
    onBellClick: () -> Unit,
    onPlantFilterClick: (PlantListFilter) -> Unit,
    onPlantCardClick: (String, String) -> Unit,
    onWaterButtonClick: (String) -> Unit
) {
    BoxWithConstraints() {
        val width = maxWidth
        val height = maxHeight
        val isScrollingDown = remember { mutableStateOf(false) }
        val fabVisibility = remember(isScrollingDown.value, plantDbIsEmpty) {
            derivedStateOf { !plantDbIsEmpty && !isScrollingDown.value }
        }

        Scaffold(
            floatingActionButton = {
                AnimatedVisibility(visible = fabVisibility.value) {
                    AddFab(
                        onClick = { onAddPlantClick() },
                        modifier = Modifier
                            .testTag("add_button")
                            .size(height * 0.05f)
                    )
                }
            },
            backgroundColor = Neutrals0,
            isFloatingActionButtonDocked = true
        ) { innerPadding ->
            Box() {
                AsyncImage(
                    R.drawable.img_plants_background,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
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
                        if (plantDbIsEmpty) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.width(width * 0.69f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(height * 0.11f))
                                    Box(
                                        Modifier
                                            .height(height * 0.26f)
                                    ) {
                                        AsyncImage(
                                            R.drawable.img_three_plants,
                                            contentDescription = null,
                                            contentScale = ContentScale.Fit
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(height * 0.05f))
                                    Text(
                                        text = stringResource(com.example.myplants.R.string.sorry),
                                        color = Neutrals900,
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle(R.font.poppins_medium),
                                        fontWeight = FontWeight(500)
                                    )
                                    Spacer(modifier = Modifier.height(height * 0.01f))
                                    Text(
                                        text = stringResource(com.example.myplants.R.string.no_plants_in_list),
                                        color = Neutrals500,
                                        fontSize = 14.sp,
                                        fontStyle = FontStyle(R.font.poppins_medium),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight(500)
                                    )
                                    Spacer(modifier = Modifier.height(height * 0.03f))
                                    IconButton(
                                        onClick = { onAddPlantClick() },
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(height * 0.015f))
                                            .background(color = Accent500)
                                            .fillMaxWidth()
                                            .height(height * 0.06f)
                                    ) {
                                        Text(
                                            text = stringResource(com.example.myplants.R.string.add_your_first_plant),
                                            color = Neutrals0,
                                            fontSize = 16.sp,
                                            fontStyle = FontStyle(R.font.poppins_regular),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight(500)
                                        )
                                    }
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.height(height * 0.02f))
                            PlantsGrid(
                                plants = plants,
                                onCardClick = { plantId, logId ->
                                    onPlantCardClick(plantId, logId)
                                },
                                onWaterButtonClick = { onWaterButtonClick(it) },
                                onIsScrollingDownStateChange = { isScrollingDown.value = it }
                            )
                        }
                    }
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
        onAddPlantClick = { },
        plantDbIsEmpty = true
    )
}
