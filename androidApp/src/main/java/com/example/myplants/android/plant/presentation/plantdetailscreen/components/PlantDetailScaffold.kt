package com.example.myplants.android.plant.presentation.plantdetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent100
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900
import com.example.myplants.android.core.presentation.theme.OtherG100
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
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val screenHeight = maxHeight
        val screenWidth = maxWidth
        val screenHorizontalPadding = screenWidth * 0.07f

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (topBox, bottomBox) = createRefs()

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topBox) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomBox.top, margin = screenHeight * -0.05f)
                        height = Dimension.fillToConstraints
                    }
                    .background(color = OtherG100)
            ) {
                val topBoxHeight = maxHeight
                val byteArray = plant?.photo?.byteArray
                if (byteArray == null) {
                    AsyncImage(
                        model = R.drawable.img_plants_background,
                        contentDescription = null, // TODO
                        contentScale = ContentScale.FillWidth
                    )
                    Box(
                        modifier = Modifier
                            .height(topBoxHeight * 0.5f)
                            .align(Alignment.Center)
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_single_plant,
                            contentDescription = null, // TODO
                            contentScale = ContentScale.FillHeight
                        )
                    }
                } else {
                    AsyncImage(
                        model = byteArray,
                        contentDescription = null, // TODO
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomBox) {
                        bottom.linkTo(parent.bottom)
                        height = Dimension.wrapContent
                    }
                    .clip(
                        RoundedCornerShape(
                            topStart = screenHeight * 0.04f,
                            topEnd = screenHeight * 0.04f
                        )
                    )
                    .background(color = Neutrals0)
            ) {
                val bottomInset = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(horizontal = screenHorizontalPadding)
                        .padding(bottom = bottomInset)
                        .fillMaxWidth()
                ) {
                    val scaleFactor = (screenHeight.value / 792f).coerceIn(0.5f, 1.5f)
                    Spacer(modifier = Modifier.height(screenHeight * 0.025f))
                    Text(
                        text = plant?.name ?: "",
                        fontWeight = FontWeight(600),
                        fontSize = 24.sp * scaleFactor,
                        color = Neutrals900,
                        fontStyle = FontStyle(R.font.poppins_regular)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.015f))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = screenHeight * 0.25f) // Adjust max height as needed
                            .verticalScroll(rememberScrollState()) // Make this box scrollable
                    ) {
                        Text(
                            text = plant?.description ?: "",
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp * scaleFactor,
                            color = Neutrals500,
                            fontStyle = FontStyle(R.font.poppins_medium),
                            lineHeight = 20.sp * scaleFactor
                        )
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                    val buttonColor: Color
                    val buttonText: String

                    if (plant?.isWatered == false) {
                        buttonColor = Accent500
                        buttonText = stringResource(SharedRes.strings.mark_as_watered.resourceId)
                    } else {
                        buttonColor = Accent100
                        buttonText = stringResource(SharedRes.strings.mark_as_not_watered.resourceId)
                    }
                    IconButton(
                        onClick = { onToggleWaterButtonPress() },
                        Modifier
                            .clip(RoundedCornerShape(screenHeight * 0.012f))
                            .fillMaxWidth()
                            .height(screenHeight * 0.06f)
                            .background(color = buttonColor)
                    ) {
                        Text(
                            text = buttonText,
                            color = Neutrals0,
                            fontWeight = FontWeight(500),
                            fontSize = 16.sp * scaleFactor,
                            fontStyle = FontStyle(R.font.poppins_medium)
                        )
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                }
            }
        }
        DetailTopBarButtons(
            onEditButtonClick = { onEditPlantButtonPress() },
            onBackButtonClick = { onBackButtonPress() },
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(
                    horizontal = screenHorizontalPadding,
                    vertical = screenHeight * 0.015f
                )
                .fillMaxWidth()
                .height(screenHeight * 0.05f)
                .align(Alignment.TopCenter)
        )
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
