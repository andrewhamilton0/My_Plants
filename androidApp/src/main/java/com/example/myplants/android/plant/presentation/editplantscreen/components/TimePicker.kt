package com.example.myplants.android.plant.presentation.editplantscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerColors
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime

@Composable
fun TimePicker(
    initialTime: LocalTime,
    timeDialogState: MaterialDialogState,
    onTimePicked: (LocalTime) -> Unit
) {
    var pickedTime by remember {
        mutableStateOf(initialTime)
    }
    MaterialDialog(
        dialogState = timeDialogState,
        backgroundColor = Neutrals0,
        buttons = {
            positiveButton(
                res = SharedRes.strings.got_it.resourceId,
                textStyle = TextStyle(
                    color = Accent500,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    fontStyle = FontStyle(R.font.poppins_medium)
                ),
                onClick = { onTimePicked(pickedTime) }
            )
            negativeButton(
                res = SharedRes.strings.cancel.resourceId,
                textStyle = TextStyle(
                    color = Neutrals500,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    fontStyle = FontStyle(R.font.poppins_medium)
                )
            )
        }
    ) {
        timepicker(
            initialTime = pickedTime,
            title = stringResource(SharedRes.strings.pick_a_time.resourceId),
            colors = object : TimePickerColors {
                override val border: BorderStroke
                    get() = BorderStroke(2.dp, Neutrals100)

                @Composable
                override fun backgroundColor(active: Boolean): State<Color> {
                    return remember(active) { mutableStateOf(if (active) Accent500 else Neutrals100) }
                }

                override fun headerTextColor(): Color {
                    return Neutrals500
                }

                @Composable
                override fun periodBackgroundColor(active: Boolean): State<Color> {
                    return remember(active) { mutableStateOf(if (active) Accent500 else Neutrals100) }
                }

                override fun selectorColor(): Color {
                    return Accent500
                }

                override fun selectorTextColor(): Color {
                    return Neutrals500
                }

                @Composable
                override fun textColor(active: Boolean): State<Color> {
                    return remember(active) { mutableStateOf(if (active) Neutrals0 else Neutrals500) }
                }
            }
        ) {
            pickedTime = it
        }
    }
}
