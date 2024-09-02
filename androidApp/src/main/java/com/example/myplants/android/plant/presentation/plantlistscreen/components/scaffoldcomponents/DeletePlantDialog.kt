package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myplants.SharedRes
import com.example.myplants.android.R
import com.example.myplants.android.core.presentation.theme.Accent500
import com.example.myplants.android.core.presentation.theme.Neutrals0
import com.example.myplants.android.core.presentation.theme.Neutrals100
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900
import com.example.myplants.android.core.presentation.theme.NotificationRed

data class DeletePlantDialogState(
    val isVisible: Boolean,
    val id: String?,
    val plantName: String?
)

@Composable
fun DeletePlantDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    plantName: String
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                    contentDescription = null, // TODO
                    tint = NotificationRed
                )
                Text(
                    text = stringResource(SharedRes.strings.are_you_sure.resourceId),
                    fontWeight = FontWeight(500),
                    fontStyle = FontStyle(R.font.poppins_medium),
                    color = Neutrals900,
                    fontSize = 16.sp
                )
            }
        },
        text = {
            Text(
                text = stringResource(
                    id = SharedRes.strings.delete_dialog.resourceId,
                    plantName
                ),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle(R.font.poppins_regular),
                color = Neutrals500,
                fontSize = 14.sp
            )
        },
        confirmButton = {
            IconButton(
                onClick = { onConfirm() },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Accent500)
                    .fillMaxWidth(0.4f)
            ) {
                Text(
                    text = stringResource(SharedRes.strings.got_it.resourceId),
                    fontStyle = FontStyle(R.font.poppins_medium),
                    color = Neutrals0,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
        },
        dismissButton = {
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Neutrals0)
                    .border(color = Neutrals100, width = 2.dp)
                    .fillMaxWidth(0.4f)
            ) {
                Text(
                    text = stringResource(SharedRes.strings.cancel.resourceId),
                    fontStyle = FontStyle(R.font.poppins_medium),
                    color = Neutrals500,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
        },
        backgroundColor = Neutrals0
    )
}
