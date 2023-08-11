package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
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
import com.example.myplants.android.core.theme.accent500
import com.example.myplants.android.core.theme.neutralus100

@Preview
@Composable
fun AddFab() {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = accent500
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
            contentDescription = null,
            tint = neutralus100
        )
    }
}
