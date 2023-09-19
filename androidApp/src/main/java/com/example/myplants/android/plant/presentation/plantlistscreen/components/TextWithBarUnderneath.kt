package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
    var fontWeightInt = 500
    var color = Neutrals300

    if (isSelected) {
        fontWeightInt = 600
        color = Accent500
    }
    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = text,
            fontWeight = FontWeight(fontWeightInt),
            fontSize = 14.sp,
            fontStyle = FontStyle(R.font.poppins_regular),
            color = color
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.52f)
                    .background(color = color, shape = RoundedCornerShape(10.dp))
                    .height(1.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextWithBarUnderneathPrev() {
    TextWithBarUnderneath(
        text = "Example Text",
        isSelected = true
    )
}
