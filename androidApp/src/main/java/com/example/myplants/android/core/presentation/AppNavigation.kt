package com.example.myplants.android.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myplants.android.plant.presentation.editplantscreen.EditPlantScreen
import com.example.myplants.android.plant.presentation.notificationscreen.NotificationScreen
import com.example.myplants.android.plant.presentation.plantdetailscreen.PlantDetailScreen
import com.example.myplants.android.plant.presentation.plantlistscreen.PlantListScreen
import com.example.myplants.android.plant.presentation.util.Screens

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.PlantList) {
        composable<Screens.PlantList> { PlantListScreen(navController = navController) }
        composable<Screens.PlantDetail> { PlantDetailScreen(navController = navController) }
        composable<Screens.Notification> { NotificationScreen() }
        composable<Screens.EditPlant> { EditPlantScreen(navController = navController) }
    }
}
