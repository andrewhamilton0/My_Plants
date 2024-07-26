package com.example.myplants.android.plant.presentation.plantlistscreen.components.plantitemholdercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Neutrals100
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900

@Composable
fun PlantHolderDescriptionBox(
    name: String,
    description: String,
    isWatered: Boolean,
    onWaterButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .background(color = Neutrals100)
    ) {
        val boxHeight = maxHeight
        val padding = maxWidth * 0.2f
        val scaleFactor = (boxHeight.value / 60f).coerceIn(0.5f, 1.5f)
        val nameFontSize = 14.sp * scaleFactor
        val descriptionFontSize = 12.sp * scaleFactor

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = padding),
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
                    fontSize = nameFontSize,
                    lineHeight = nameFontSize * 1.4f,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = description,
                    color = Neutrals500,
                    maxLines = 1,
                    minLines = 1,
                    fontStyle = FontStyle(R.font.poppins_regular),
                    fontSize = descriptionFontSize,
                    lineHeight = descriptionFontSize * 1.4f,
                    fontWeight = FontWeight(400)
                )
            }
            WaterButton(
                isWatered = isWatered,
                onClick = onWaterButtonClick,
                modifier = Modifier.size(boxHeight * 1f)
            )
        }
    }
}
