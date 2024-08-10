package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals100

@Composable
fun AddFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        IconButton(
            onClick = onClick,
            modifier = modifier
                .clip(RoundedCornerShape(height * 0.31f))
                .background(color = Accent500)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                contentDescription = stringResource(id = SharedRes.strings.add_plant.resourceId),
                tint = Neutrals100,
                modifier = Modifier.size(height * 0.5f)
            )
        }
    }
}
