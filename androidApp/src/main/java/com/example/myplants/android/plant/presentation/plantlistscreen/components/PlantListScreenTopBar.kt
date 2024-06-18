package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Neutrals900

@Composable
fun PlantListScreenTopBar(
    isNotificationBellNotifying: Boolean,
    onNotificationBellClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = stringResource(id = SharedRes.strings.lets_care.resourceId),
                color = Neutrals900,
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                fontStyle = FontStyle(R.font.poppins_semibold)
            )
            Text(
                text = stringResource(id = SharedRes.strings.my_plants.resourceId),
                color = Neutrals900,
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                fontStyle = FontStyle(R.font.poppins_semibold)
            )
        }
        NotificationBellBtn(
            onClick = onNotificationBellClick,
            isNotifying = isNotificationBellNotifying
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlantListScreenTopBarPrev() {
    PlantListScreenTopBar(
        isNotificationBellNotifying = true,
        onNotificationBellClick = { Unit }
    )
}
