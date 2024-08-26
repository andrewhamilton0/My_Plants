package com.example.myplants.android.plant.presentation.plantdetailscreen.components.scaffoldcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals100
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.UiPlantDetailItem
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.desc.PluralFormatted
import dev.icerock.moko.resources.desc.StringDesc

@Composable
fun WaterDescriptionBox(
    plant: UiPlantDetailItem?,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
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
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(width * 0.03f))
                .background(color = Neutrals0)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(height * 0.6f)
                    .fillMaxWidth()
            ) {
                TextBox(
                    topText = stringResource(SharedRes.strings.size.resourceId),
                    bottomText = sizeText
                )
                CustomSpacer()
                TextBox(
                    topText = stringResource(SharedRes.strings.water.resourceId),
                    bottomText = plant?.waterAmount ?: ""
                )
                CustomSpacer()
                TextBox(
                    topText = stringResource(SharedRes.strings.frequency.resourceId),
                    bottomText = StringDesc.PluralFormatted(
                        PluralsResource(SharedRes.plurals.times_per_week.resourceId),
                        plant?.waterFrequencyWeekly ?: 1,
                        plant?.waterFrequencyWeekly ?: 1
                    ).toString(context)
                )
            }
        }
    }
}

@Composable
private fun CustomSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .background(color = Neutrals100)
            .fillMaxHeight()
            .width(1.dp)
    )
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
            .fillMaxHeight()
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
