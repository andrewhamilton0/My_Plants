package com.example.myplants.android

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myplants.android.plant.presentation.editplantscreen.EditPlantScreen
import com.example.myplants.android.plant.presentation.notificationscreen.NotificationScreen
import com.example.myplants.android.plant.presentation.plantdetailscreen.PlantDetailScreen
import com.example.myplants.android.plant.presentation.plantlistscreen.PlantListScreen
import com.example.myplants.android.plant.presentation.util.Screens

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.apply {
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.PlantList) {
                    composable<Screens.PlantList> { PlantListScreen(navController = navController) }
                    composable<Screens.PlantDetail> { PlantDetailScreen(navController = navController) }
                    composable<Screens.Notification> { NotificationScreen() }
                    composable<Screens.EditPlant> { EditPlantScreen() }
                }
            }
        }
    }
}
