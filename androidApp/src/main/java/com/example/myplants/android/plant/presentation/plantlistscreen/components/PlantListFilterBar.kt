package com.example.myplants.android.plant.presentation.plantlistscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.android.R
import com.example.myplants.plants.presentation.plantlistscreen.PlantListFilter
import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenEvent

@Composable
fun PlantListFilterBar(
    onClick: (PlantListFilter) -> Unit,
    currentlySelected: PlantListFilter
) {
    Row {
        TextWithBarUnderneath(
            text = stringResource(id = R.string.upcoming),
            isSelected = currentlySelected == PlantListFilter.UPCOMING,
            onClick = {
                onClick(PlantListFilter.UPCOMING)
            }
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextWithBarUnderneath(
            text = stringResource(id = R.string.forgot_to_water),
            isSelected = currentlySelected == PlantListFilter.FORGOT_TO_WATER,
            onClick = {
                onClick(PlantListFilter.FORGOT_TO_WATER)
            }
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextWithBarUnderneath(
            text = stringResource(id = R.string.history),
            isSelected = currentlySelected == PlantListFilter.HISTORY,
            onClick = {
                onClick(PlantListFilter.HISTORY)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlantListFilterBarPrev() {
    PlantListFilterBar(
        currentlySelected = PlantListFilter.UPCOMING,
        onClick = { Unit }
    )
}
