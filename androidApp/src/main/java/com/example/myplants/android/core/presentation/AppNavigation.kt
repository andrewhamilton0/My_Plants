package com.example.myplants.android.core.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myplants.android.plant.presentation.editplantscreen.EditPlantScreen
import com.example.myplants.android.plant.presentation.notificationscreen.NotificationScreen
import com.example.myplants.android.plant.presentation.plantdetailscreen.PlantDetailScreen
import com.example.myplants.android.plant.presentation.plantlistscreen.PlantListScreen
import com.example.myplants.android.plant.presentation.util.Screens

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.PlantList,
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            }
    ) {
        composable<Screens.PlantList> { PlantListScreen(navController = navController) }
        composable<Screens.Notification> { NotificationScreen() }
        composable<Screens.PlantDetail> {
            val args = it.toRoute<Screens.PlantDetail>()
            PlantDetailScreen(navController = navController, plantId = args.plantId, logId = args.logId)
        }
        composable<Screens.EditPlant> {
            val args = it.toRoute<Screens.EditPlant>()
            EditPlantScreen(navController = navController, plantId = args.plantId)
        }
    }
}
