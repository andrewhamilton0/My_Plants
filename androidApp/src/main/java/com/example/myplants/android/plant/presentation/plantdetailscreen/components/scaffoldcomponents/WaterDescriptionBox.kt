package com.example.myplants.android.plant.presentation.plantdetailscreen.components.scaffoldcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.UiPlantDetailItem

@Composable
fun WaterDescriptionBox(
    plant: UiPlantDetailItem?
) {
    BoxWithConstraints() {
        val height = maxHeight
        val width = maxWidth
        val context = LocalContext.current

        val sizeText = stringResource(
            when (plant?.plantSize) {
                PlantSize.SMALL -> SharedRes.strings.small.resourceId
                PlantSize.MEDIUM -> SharedRes.strings.medium.resourceId
                PlantSize.LARGE -> SharedRes.strings.large.resourceId
                PlantSize.EXTRA_LARGE -> SharedRes.strings.extra_large.resourceId
                else -> SharedRes.strings.small.resourceId
            }
        )
        TextBox(
            topText = stringResource(SharedRes.strings.size.resourceId),
            bottomText = sizeText
        )
        TextBox(
            topText = stringResource(SharedRes.strings.water.resourceId),
            bottomText = plant?.waterAmount ?: ""
        )
        TextBox(
            topText = stringResource(SharedRes.strings.frequency.resourceId),
            bottomText = SharedRes.plurals.times_per_week.getQuantityString(
                context = context,
                number = plant?.waterFrequencyWeekly ?: 0
            )
        )
    }
}

@Composable
private fun TextBox(
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .wrapContentWidth()
    ) {
        val height = maxHeight
        val scaleFactor = (height.value / 34f).coerceIn(0.5f, 1.5f)
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = topText,
                color = Neutrals500,
                fontWeight = FontWeight(500),
                fontSize = 10.sp * scaleFactor,
                fontStyle = FontStyle(R.font.poppins_regular)
            )
            Text(
                text = bottomText,
                color = Accent500,
                fontWeight = FontWeight(400),
                fontSize = 12.sp * scaleFactor,
                fontStyle = FontStyle(R.font.poppins_regular)
            )
        }
    }
}

@Preview
@Composable
fun WaterDescriptionBoxPreview() {
    WaterDescriptionBox(
        UiPlantDetailItem(
            plantId = "",
            waterAmount = "5ML",
            name = "Aguacate",
            description = "Deliciouss",
            plantSize = PlantSize.MEDIUM,
            isWatered = true,
            photo = null,
            waterFrequencyWeekly = 2
        )
    )
}
