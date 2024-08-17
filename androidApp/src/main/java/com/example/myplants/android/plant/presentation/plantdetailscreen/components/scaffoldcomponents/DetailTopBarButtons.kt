package com.example.myplants.android.plant.presentation.plantdetailscreen.components.scaffoldcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.android.core.presentation.theme.Neutrals0

@Composable
fun DetailTopBarButtons(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shape = CircleShape
            val color = Neutrals0
            // Button 1
            IconButton(
                onClick = { },
                modifier = Modifier
                    .clip(shape)
                    .size(height)
                    .background(color = color)
            ) { }
            // Button 2
            IconButton(
                onClick = { },
                modifier = Modifier
                    .clip(shape)
                    .size(height)
                    .background(color = color)
            ) { }
        }
    }
}

@Preview
@Composable
fun DetailTopBarButtonsPreview() {
    DetailTopBarButtons(modifier = Modifier.width(300.dp).height(20.dp))
}
