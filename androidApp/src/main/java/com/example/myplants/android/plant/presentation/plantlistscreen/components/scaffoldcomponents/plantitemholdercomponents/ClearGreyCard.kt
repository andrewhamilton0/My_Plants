package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.plantitemholdercomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
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
        val height = maxHeight
        val cornerRadius = maxHeight * 0.2f
        val horizontalPadding = maxHeight * 0.3f
        val scaleFactor = (height.value / 20f).coerceIn(0.5f, 1.5f)
        val fontSize = 9.sp * scaleFactor

        Surface(
            shape = RoundedCornerShape(cornerRadius),
            color = GrayishBlack.copy(alpha = 0.4f),
            modifier = Modifier
                .wrapContentWidth().height(height)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()

            ) {
                Text(
                    text = text,
                    color = Neutrals0,
                    fontSize = fontSize,
                    fontStyle = FontStyle(R.font.poppins_medium),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = horizontalPadding)
                )
            }
        }
    }
}
