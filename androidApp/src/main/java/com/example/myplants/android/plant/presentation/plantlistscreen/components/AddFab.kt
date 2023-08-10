package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myplants.android.R


@Composable
fun AddFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
        // TODO ADD COLOR MODIFIER
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_notification_bell),
            contentDescription = null // TODO WRITE DESCRIPTION
            // TODO ADD TINT MODIFIER
        )
    }
}
