package com.example.myplants.android.plant.data.workmanager

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

class PlantWorkerManager(private val context: Context) {
    fun start() {
        scheduleDailyPlantAlarm()
    }
    private fun scheduleDailyPlantAlarm() {
        val dailyPlantAlarmRequest = PeriodicWorkRequestBuilder<DailyPlantAlarmUpdateWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "DAILY_PLANT_ALARM",
            ExistingPeriodicWorkPolicy.UPDATE,
            dailyPlantAlarmRequest
        )
    }

    private fun calculateInitialDelay(): Long {
        val calendar = Calendar.getInstance()
        val now = calendar.timeInMillis

        calendar.apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return calendar.timeInMillis - now
    }
}
