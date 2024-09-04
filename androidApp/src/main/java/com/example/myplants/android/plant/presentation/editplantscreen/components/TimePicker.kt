package com.example.myplants.android.plant.presentation.editplantscreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalTime


@Composable
fun TimePicker(
    initialTime: LocalTime,
    timeDialogState: MaterialDialogState,
    onTimePicked: (LocalTime) -> Unit
){
    var pickedTime by remember {
        mutableStateOf(initialTime)
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Okay")
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = pickedTime,
            title = "Pick a time"
        ) {
            pickedTime = it
        }
    }
}