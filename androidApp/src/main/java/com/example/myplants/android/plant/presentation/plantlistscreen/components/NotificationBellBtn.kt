package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.android.core.theme.neutralus100
import com.example.myplants.android.core.theme.neutralus500
import com.example.myplants.android.core.theme.notificationRed

@Composable
fun NotificationBellBtn(
    onClick: () -> Unit,
    modifier: Modifier,
    isNotifying: Boolean
) {
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .clip(CircleShape)
                .background(color = neutralus100)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_notification_bell),
                contentDescription = stringResource(id = R.string.view_notifications),
                tint = neutralus500
            )
        }
        if(isNotifying){
            Badge(
                modifier = Modifier
                    .size(12.dp),
                backgroundColor = notificationRed
            ) {
            }
        }

    }
}
