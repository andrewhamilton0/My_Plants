package com.example.myplants.android.plant.presentation.editplantscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.myplants.android.core.presentation.theme.Neutrals300
import com.example.myplants.android.core.presentation.theme.Neutrals500
import com.example.myplants.android.core.presentation.theme.Neutrals900
import com.example.myplants.featureplant.domain.plant.PlantSize

data class PlantSizeDialogState(
    val isVisible: Boolean
)

@Composable
fun PlantSizeDialog(
    plantSize: PlantSize?,
    onDismiss: () -> Unit,
    onConfirm: (PlantSize) -> Unit
) {
    val sizeSelected = remember { mutableStateOf(plantSize ?: PlantSize.SMALL) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(SharedRes.strings.plant_size.resourceId),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle(R.font.poppins_medium),
                color = Neutrals900,
                fontSize = 16.sp
            )
        },
        text = {
            Column {
                SizeOption(
                    isSelected = sizeSelected.value == PlantSize.SMALL,
                    text = stringResource(SharedRes.strings.small.resourceId),
                    onButtonClick = { sizeSelected.value = PlantSize.SMALL }
                )
                SizeOption(
                    isSelected = sizeSelected.value == PlantSize.MEDIUM,
                    text = stringResource(SharedRes.strings.medium.resourceId),
                    onButtonClick = { sizeSelected.value = PlantSize.MEDIUM }
                )
                SizeOption(
                    isSelected = sizeSelected.value == PlantSize.LARGE,
                    text = stringResource(SharedRes.strings.large.resourceId),
                    onButtonClick = { sizeSelected.value = PlantSize.LARGE }
                )
                SizeOption(
                    isSelected = sizeSelected.value == PlantSize.EXTRA_LARGE,
                    text = stringResource(SharedRes.strings.extra_large.resourceId),
                    onButtonClick = { sizeSelected.value = PlantSize.EXTRA_LARGE }
                )
            }
        },
        confirmButton = {
            IconButton(
                onClick = { onConfirm(sizeSelected.value) },
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

@Composable
private fun SizeOption(
    isSelected: Boolean,
    text: String,
    onButtonClick: () -> Unit
) {
    val textColor: Color
    val buttonColor: Color
    val buttonVectorInt: Int

    if (isSelected) {
        textColor = Neutrals900
        buttonColor = Accent500
        buttonVectorInt = R.drawable.ic_selected_radio
    } else {
        textColor = Neutrals500
        buttonColor = Neutrals300
        buttonVectorInt = R.drawable.ic_unselected_radio
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .clickable { onButtonClick() }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(buttonVectorInt),
            contentDescription = null, // TODO
            tint = buttonColor
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight(500)
        )
    }
}
