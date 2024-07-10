package com.example.myplants.android

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.example.myplants.android.core.presentation.AppNavigation
import com.example.myplants.android.plant.presentation.PlantNotification

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.apply {
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }

        val plantNotification = PlantNotification(this, this::class.java)
        plantNotification.createNotificationChannels()

        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}
