package com.example.myplants.android.plant.presentation.plantlistscreen.components.scaffoldcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplants.SharedRes
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListFilter

@Composable
fun PlantListFilterBar(
    onClick: (PlantListFilter) -> Unit,
    currentlySelected: PlantListFilter,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        TextWithBarUnderneath(
            text = stringResource(id = SharedRes.strings.upcoming.resourceId),
            isSelected = currentlySelected == PlantListFilter.UPCOMING,
            onClick = {
                onClick(PlantListFilter.UPCOMING)
            }
        )
        TextWithBarUnderneath(
            text = stringResource(id = SharedRes.strings.forgot_to_water.resourceId),
            isSelected = currentlySelected == PlantListFilter.FORGOT_TO_WATER,
            onClick = {
                onClick(PlantListFilter.FORGOT_TO_WATER)
            }
        )
        TextWithBarUnderneath(
            text = stringResource(id = SharedRes.strings.history.resourceId),
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
        onClick = { },
        modifier = Modifier.width(150.dp)
    )
}
