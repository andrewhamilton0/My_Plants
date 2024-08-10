package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.plantitemholdercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.OtherG100

@Composable
fun PlantImageBox(
    nextWaterDate: String,
    waterAmount: String,
    plantName: String,
    byteArray: ByteArray?,
    modifier: Modifier = Modifier
) {
    val imageContentDescription = stringResource(
        id = SharedRes.strings.plant_photo.resourceId,
        plantName
    )
    val context = LocalContext.current

    BoxWithConstraints(
        Modifier
            .background(color = OtherG100)
            .fillMaxWidth()
            .then(modifier)
    ) {
        val padding = maxWidth * 0.05f
        val greyCardHeight = maxHeight * 0.10f
        val maxGreyCardWidth = maxWidth * 0.45f
        val greyCardsPadding = maxHeight * 0.02f
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val model = remember(byteArray) {
                byteArray?.let {
                    ImageRequest.Builder(context)
                        .placeholder(R.drawable.ic_single_plant)
                        .data(byteArray)
                        .build()
                } ?: R.drawable.ic_single_plant
            }
            AsyncImage(
                model = model,
                contentDescription = imageContentDescription,
                contentScale = ContentScale.Fit
            )
        }
        Box(
            modifier = Modifier.padding(padding),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(greyCardsPadding)
            ) {
                val greyCardModifier = Modifier
                    .height(greyCardHeight)
                    .sizeIn(maxWidth = maxGreyCardWidth)
                ClearGreyCard(text = waterAmount, modifier = greyCardModifier)
                ClearGreyCard(text = nextWaterDate, modifier = greyCardModifier)
            }
        }
    }
}
