package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals300

@Composable
fun TextWithBarUnderneath(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val fontWeightInt = if (isSelected) 600 else 500
    val color = if (isSelected) Accent500 else Neutrals300

    BoxWithConstraints {
        val height = maxHeight
        val scaleFactor = (height.value / 650f).coerceIn(0.5f, 1.5f)
        val fontSize = 14.sp * scaleFactor
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            Text(
                modifier = Modifier.clickable { onClick() },
                text = text,
                fontWeight = FontWeight(fontWeightInt),
                fontSize = fontSize,
                fontStyle = FontStyle(R.font.poppins_regular),
                color = color
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.52f)
                        .background(color = color, shape = RoundedCornerShape(10.dp))
                        .height(2.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextWithBarUnderneathPrev() {
    TextWithBarUnderneath(
        text = "Example Text",
        isSelected = true,
        onClick = { }
    )
}
