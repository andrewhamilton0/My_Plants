package com.example.myplants.android.plant.presentation.plantlistscreen.components.plantitemholdercomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.GrayishBlack
import com.example.myplants.android.core.presentation.theme.Neutrals0

@Composable
fun ClearGreyCard(
    text: String,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val minDimension = minOf(maxWidth, maxHeight)
        val cornerRadius = minDimension * 0.04f
        val horizontalPadding = minDimension * 0.06f
        val verticalPadding = minDimension * 0.02f
        val scaleFactor = (minDimension.value / 100f).coerceIn(0.8f, 1.2f)
        val fontSize = 10.sp * scaleFactor
        Card(
            shape = RoundedCornerShape(cornerRadius),
            backgroundColor = GrayishBlack.copy(alpha = 0.2f),
            elevation = 0.1.dp
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Neutrals0,
                    fontSize = fontSize,
                    fontStyle = FontStyle(R.font.poppins_medium),
                    text = text,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}
