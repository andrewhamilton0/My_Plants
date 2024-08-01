package com.example.myplants.android.plant.presentation.plantlistscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.myplants.SharedRes
import com.example.myplants.android.plant.presentation.plantlistscreen.components.plantitemholdercomponents.PlantHolderDescriptionBox
import com.example.myplants.android.plant.presentation.plantlistscreen.components.plantitemholdercomponents.PlantImageBox
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.UiPlantItem
import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantItemHolder(
    plant: UiPlantItem,
    onWaterButtonClick: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val locale = Locale.getDefault()

    val today = stringResource(id = SharedRes.strings.today.resourceId)
    val tomorrow = stringResource(id = SharedRes.strings.tomorrow.resourceId)

    val nextWaterDate by remember(plant.dateDescriptor, locale, today, tomorrow) {
        derivedStateOf {
            when (val dateDescriptor = plant.dateDescriptor) {
                is DateDescriptor.Date -> {
                    val formatter = DateTimeFormatter.ofPattern("MMM dd").withLocale(locale)
                    dateDescriptor.date.toJavaLocalDate().format(formatter)
                }
                DateDescriptor.Today -> today
                DateDescriptor.Tomorrow -> tomorrow
            }
        }
    }
    Card(
        modifier = modifier
            .clickable { onCardClick() }
            .testTag("plant_card")
    ) {
        Column {
            PlantImageBox(
                nextWaterDate = nextWaterDate,
                waterAmount = plant.waterAmount,
                plantName = plant.name,
                byteArray = plant.photo?.byteArray,
                modifier = Modifier
                    .aspectRatio(167f / 196f)
                    .fillMaxSize()
            )
            PlantHolderDescriptionBox(
                name = plant.name,
                description = plant.description,
                isWatered = plant.isWatered,
                onWaterButtonClick = onWaterButtonClick,
                modifier = Modifier
                    .aspectRatio(167f / 60f)
                    .fillMaxSize()
            )
        }
    }
}
