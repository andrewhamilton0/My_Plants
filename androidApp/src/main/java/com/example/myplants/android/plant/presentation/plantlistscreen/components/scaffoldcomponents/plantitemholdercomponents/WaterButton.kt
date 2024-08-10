package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents.plantitemholdercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.coerceAtMost
import com.example.myplants.R.string
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent100
import com.example.myplants.android.core.presentation.theme.Accent600
import com.example.myplants.android.core.presentation.theme.Neutrals0

@Composable
fun WaterButton(
    isWatered: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColor = if (isWatered) Accent100 else Accent600
    val iconColor = Neutrals0
    val imageVector = ImageVector.vectorResource(
        id = if (isWatered) R.drawable.ic_check else R.drawable.ic_water
    )
    val description = stringResource(
        if (isWatered) string.mark_plant_watered_button else string.mark_plant_not_watered_button
    )

    BoxWithConstraints(modifier = modifier) {
        val buttonSize = maxWidth.coerceAtMost(maxHeight)
        val buttonShape = RoundedCornerShape(buttonSize * .17f)
        val iconSize = buttonSize * 0.67f

        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .clip(buttonShape)
                .background(color = buttonColor)
                .size(buttonSize)
        ) {
            Icon(
                imageVector = imageVector,
                tint = iconColor,
                modifier = Modifier.size(iconSize),
                contentDescription = description
            )
        }
    }
}
