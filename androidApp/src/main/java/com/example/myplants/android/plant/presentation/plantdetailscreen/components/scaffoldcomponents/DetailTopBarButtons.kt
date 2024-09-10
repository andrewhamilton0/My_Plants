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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals900

@Composable
fun DetailTopBarButtons(
    onEditButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
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
            var enabled by remember { mutableStateOf(true) }
            IconButton(
                enabled = enabled,
                onClick = {
                    enabled = false
                    onBackButtonClick()
                },
                modifier = Modifier
                    .clip(shape)
                    .size(height)
                    .background(color = color)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                    contentDescription = "", // TODO WRITE CONTENT DESCRIPTION
                    tint = Neutrals900,
                    modifier = Modifier
                        .size(height * 0.55f)
                )
            }
            IconButton(
                enabled = enabled,
                onClick = {
                    enabled = false
                    onEditButtonClick()
                },
                modifier = Modifier
                    .clip(shape)
                    .size(height)
                    .background(color = color)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                    contentDescription = "", // TODO WRITE CONTENT DESCRIPTION
                    tint = Neutrals900,
                    modifier = Modifier
                        .size(height * 0.45f)
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailTopBarButtonsPreview() {
    DetailTopBarButtons(
        onBackButtonClick = {},
        onEditButtonClick = {},
        modifier = Modifier.width(300.dp).height(20.dp)
    )
}
