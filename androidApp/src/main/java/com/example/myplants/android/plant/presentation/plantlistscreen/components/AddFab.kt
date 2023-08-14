package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.android.core.theme.Accent500
import com.example.myplants.android.core.theme.Neutrals100

@Composable
fun AddFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = Accent500
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
            contentDescription = stringResource(id = R.string.add_plant),
            tint = Neutrals100
        )
    }
}
