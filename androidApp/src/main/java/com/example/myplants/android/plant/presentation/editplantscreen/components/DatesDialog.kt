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
import kotlinx.datetime.DayOfWeek

data class DatesDialogState(
    val isVisible: Boolean
)

@Composable
fun DatesDialog(
    daysOfWeek: Set<DayOfWeek>?,
    onDismiss: () -> Unit,
    onConfirm: (Set<DayOfWeek>) -> Unit
) {
    val datesSelected = remember { mutableStateOf(daysOfWeek ?: emptySet()) }

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
                DateOption(
                    isSelected = datesSelected.value.size == 7,
                    text = stringResource(SharedRes.strings.everyday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.size == 7) {
                            datesSelected.value = emptySet()
                        } else {
                            datesSelected.value = setOf(
                                DayOfWeek.MONDAY,
                                DayOfWeek.TUESDAY,
                                DayOfWeek.WEDNESDAY,
                                DayOfWeek.THURSDAY,
                                DayOfWeek.FRIDAY,
                                DayOfWeek.SATURDAY,
                                DayOfWeek.SUNDAY
                            )
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.MONDAY),
                    text = stringResource(SharedRes.strings.monday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.MONDAY)) {
                            datesSelected.value -= DayOfWeek.MONDAY
                        } else {
                            datesSelected.value += DayOfWeek.MONDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.TUESDAY),
                    text = stringResource(SharedRes.strings.tuesday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.TUESDAY)) {
                            datesSelected.value -= DayOfWeek.TUESDAY
                        } else {
                            datesSelected.value += DayOfWeek.TUESDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.WEDNESDAY),
                    text = stringResource(SharedRes.strings.wednesday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.WEDNESDAY)) {
                            datesSelected.value -= DayOfWeek.WEDNESDAY
                        } else {
                            datesSelected.value += DayOfWeek.WEDNESDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.THURSDAY),
                    text = stringResource(SharedRes.strings.thursday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.THURSDAY)) {
                            datesSelected.value -= DayOfWeek.THURSDAY
                        } else {
                            datesSelected.value += DayOfWeek.THURSDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.FRIDAY),
                    text = stringResource(SharedRes.strings.friday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.FRIDAY)) {
                            datesSelected.value -= DayOfWeek.FRIDAY
                        } else {
                            datesSelected.value += DayOfWeek.FRIDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.SATURDAY),
                    text = stringResource(SharedRes.strings.saturday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.SATURDAY)) {
                            datesSelected.value -= DayOfWeek.SATURDAY
                        } else {
                            datesSelected.value += DayOfWeek.SATURDAY
                        }
                    }
                )
                DateOption(
                    isSelected = datesSelected.value.contains(DayOfWeek.SUNDAY),
                    text = stringResource(SharedRes.strings.sunday.resourceId),
                    onButtonClick = {
                        if (datesSelected.value.contains(DayOfWeek.SUNDAY)) {
                            datesSelected.value -= DayOfWeek.SUNDAY
                        } else {
                            datesSelected.value += DayOfWeek.SUNDAY
                        }
                    }
                )
            }
        },
        confirmButton = {
            IconButton(
                onClick = { onConfirm(datesSelected.value) },
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
private fun DateOption(
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
