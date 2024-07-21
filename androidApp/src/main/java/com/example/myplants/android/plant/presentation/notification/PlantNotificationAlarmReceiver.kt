package com.example.myplants.android.plant.presentation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.presentation.plant.util.NotificationChannels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class PlantNotificationAlarmReceiver() : BroadcastReceiver() {

    private val plantRepository: PlantRepository by inject(PlantRepository::class.java)
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onReceive(context: Context, intent: Intent) {
        val plantId = intent.getStringExtra("plant_id")
        val waterLogId = intent.getStringExtra("water_log_id")
        val notificationChannel = intent.getStringExtra("notification_channel")
        val plantNotificationManager = PlantNotificationManager(context)

        coroutineScope.launch {
            val plant = plantId?.let { plantRepository.getPlant(it) }?.first()
            if (plant != null && waterLogId != null) {
                when (notificationChannel) {
                    NotificationChannels.WATER_CHANNEL_ID -> {
                        plantNotificationManager.showWaterNotification(plant, waterLogId.hashCode())
                    }
                    NotificationChannels.FORGOT_WATER_CHANNEL_ID -> {
                        plantNotificationManager.showForgotToWaterNotification(plant, waterLogId.hashCode())
                    }
                    else -> Unit
                }
            }
            coroutineScope.cancel()
        }
    }
}
