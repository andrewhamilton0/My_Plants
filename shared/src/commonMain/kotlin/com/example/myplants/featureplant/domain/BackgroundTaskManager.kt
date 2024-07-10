package com.example.myplants.featureplant.domain

interface BackgroundTaskManager {
    fun scheduleNotifications()
    fun generateWaterDays()
}
