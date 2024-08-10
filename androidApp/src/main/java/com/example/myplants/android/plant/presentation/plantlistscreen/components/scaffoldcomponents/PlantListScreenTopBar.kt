package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Neutrals900

@Composable
fun PlantListScreenTopBar(
    isNotificationBellNotifying: Boolean,
    onNotificationBellClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val height = maxHeight
        val scaleFactor = (height.value / 64f).coerceIn(0.5f, 1.5f)
        val fontSize = 24.sp * scaleFactor

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = SharedRes.strings.lets_care.resourceId),
                    color = Neutrals900,
                    fontWeight = FontWeight(600),
                    fontSize = fontSize,
                    fontStyle = FontStyle(R.font.poppins_semibold)
                )
                Text(
                    text = stringResource(id = SharedRes.strings.my_plants.resourceId),
                    color = Neutrals900,
                    fontWeight = FontWeight(600),
                    fontSize = fontSize,
                    fontStyle = FontStyle(R.font.poppins_semibold)
                )
            }
            NotificationBellBtn(
                onClick = onNotificationBellClick,
                isNotifying = isNotificationBellNotifying,
                modifier = Modifier.size(height * 0.63f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlantListScreenTopBarPrev() {
    PlantListScreenTopBar(
        isNotificationBellNotifying = true,
        onNotificationBellClick = { Unit },
        modifier = Modifier.height(64.dp).width(300.dp)
    )
}
