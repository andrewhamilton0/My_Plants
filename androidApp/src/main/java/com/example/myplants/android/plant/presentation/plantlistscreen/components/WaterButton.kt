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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.android.core.theme.Accent600
import com.example.myplants.android.core.theme.Neutrals0

@Preview
@Composable
fun WaterButton() {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = Accent600)
            .size(24.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_water),
            tint = Neutrals0,
            modifier = Modifier.size(16.dp),
            contentDescription = null // TODO Fill this out
        )
    }
}
