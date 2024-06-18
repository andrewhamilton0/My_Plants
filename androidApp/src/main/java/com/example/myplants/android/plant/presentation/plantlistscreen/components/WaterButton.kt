package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent100
import com.example.myplants.android.core.presentation.theme.Accent600
import com.example.myplants.android.core.presentation.theme.Neutrals0

@Composable
fun WaterButton(
    isWatered: Boolean,
    onClick: () -> Unit
) {
    val color = if (isWatered) Accent100 else Accent600
    val imageVector = ImageVector.vectorResource(
        id = if (isWatered) R.drawable.ic_check else R.drawable.ic_water
    )
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = color)
            .size(24.dp)
    ) {
        Icon(
            imageVector = imageVector,
            tint = Neutrals0,
            modifier = Modifier.size(16.dp),
            contentDescription = null // TODO Fill this out
        )
    }
}
