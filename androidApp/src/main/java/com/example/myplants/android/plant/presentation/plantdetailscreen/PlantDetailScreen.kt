package com.example.myplants.android.plant.presentation.plantdetailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun PlantDetailScreen(navController: NavController) {
    Scaffold() { innerPadding ->
        Box(
            Modifier.padding(innerPadding)
        ) {
            Column {
                Text(text = "Second Page :D")
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Go to previous page")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Add plant")
                }
            }
        }
    }
}
