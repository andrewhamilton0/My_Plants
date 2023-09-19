package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.GrayishBlack
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals100
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900
import com.example.myplants.android.core.presentation.theme.OtherG100
import com.example.myplants.android.plant.presentation.plantlistscreen.UiPlantItem

@Composable
fun PlantItemHolder(
    plant: UiPlantItem
) {
    Card {
        Column(
            modifier = Modifier.width(167.dp)
        ) {
            PlantImageBox(
                nextWaterDate = plant.nextWaterDate,
                plantImageVector = plant.imageVector,
                waterAmount = plant.waterAmount,
                plantName = plant.name
            )
            PlantHolderDescriptionBox(
                name = plant.name,
                description = plant.description,
                isWatered = plant.isWatered
            )
        }
    }
}

@Composable
fun ClearGreyCard(
    text: String
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = GrayishBlack.copy(alpha = 0.2f),
        elevation = 0.1.dp
    ) {
        Box(
            modifier = Modifier
                .padding(start = 6.dp, top = 2.dp, end = 6.dp, bottom = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Neutrals0,
                fontSize = 10.sp,
                fontStyle = FontStyle(R.font.poppins_medium),
                text = text,
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Composable
fun PlantImageBox(
    plantImageVector: ImageVector?,
    nextWaterDate: String,
    waterAmount: String,
    plantName: String
) {
    Box(
        Modifier
            .background(
                color = OtherG100
            )
            .height(196.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = plantImageVector ?: ImageVector.vectorResource(
                    id = R.drawable.ic_single_plant
                ),
                contentDescription = stringResource(id = R.string.plant_photo, plantName)
            )
        }
        Box(
            modifier = Modifier
                .padding(12.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column {
                ClearGreyCard(text = waterAmount)
                Spacer(modifier = Modifier.height(4.dp))
                ClearGreyCard(text = nextWaterDate)
            }
        }
    }
}

@Composable
fun PlantHolderDescriptionBox(
    name: String,
    description: String,
    isWatered: Boolean
) {
    Box(
        modifier = Modifier
            .background(color = Neutrals100)
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = name,
                    color = Neutrals900,
                    maxLines = 1,
                    minLines = 1,
                    fontStyle = FontStyle(R.font.poppins_semibold),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = description,
                    color = Neutrals500,
                    maxLines = 1,
                    minLines = 1,
                    fontStyle = FontStyle(R.font.poppins_regular),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400)
                )
            }
            WaterButton(
                isWatered = isWatered
            )
        }
    }
}
