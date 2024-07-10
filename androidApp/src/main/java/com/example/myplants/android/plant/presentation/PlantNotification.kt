package com.example.myplants.android.plant.presentation

import android.app.Activity
import android.content.Context
import com.example.myplants.android.core.presentation.util.NotificationChannels
import com.example.myplants.android.core.presentation.util.NotificationHelper
import com.example.myplants.featureplant.domain.plant.Plant

class PlantNotification(
    private val context: Context,
    private val activity: Class<out Activity>
) {
    private val notificationHelper = NotificationHelper(context)

    fun createNotificationChannels() {
        createWaterNotificationChannel()
        createForgotToWaterNotificationChannel()
    }

    fun showWaterNotification(plant: Plant, notificationId: Int) {
        notificationHelper.showNotification(
            channelId = NotificationChannels.WATER_CHANNEL_ID,
            activityClass = activity,
            title = context.getString(com.example.myplants.R.string.needs_water_notification, plant.name),
            message = context.getString(com.example.myplants.R.string.click_here_for_more_information),
            notificationId = notificationId
        )
    }
    fun showForgotToWaterNotification(plant: Plant, notificationId: Int) {
        notificationHelper.showNotification(
            channelId = NotificationChannels.WATER_CHANNEL_ID,
            activityClass = activity,
            title = context.getString(com.example.myplants.R.string.forgot_to_water_notification, plant.name),
            message = context.getString(com.example.myplants.R.string.click_here_for_more_information),
            notificationId = notificationId
        )
    }

    private fun createWaterNotificationChannel() {
        notificationHelper.createNotificationChannel(
            name = context.getString(com.example.myplants.R.string.water_channel),
            channelId = NotificationChannels.WATER_CHANNEL_ID,
            descriptionText = context.getString(com.example.myplants.R.string.water_channel_description)
        )
    }
    private fun createForgotToWaterNotificationChannel() {
        notificationHelper.createNotificationChannel(
            name = context.getString(com.example.myplants.R.string.forgot_to_water_channel),
            channelId = NotificationChannels.FORGOT_WATER_CHANNEL_ID,
            descriptionText = context.getString(com.example.myplants.R.string.forgot_to_water_channel_description)
        )
    }
}
