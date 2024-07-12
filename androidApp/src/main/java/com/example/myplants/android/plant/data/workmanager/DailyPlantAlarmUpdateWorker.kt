package com.example.myplants.android.plant.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myplants.featureplant.domain.PlantManagementService
import org.koin.java.KoinJavaComponent.inject

class DailyPlantAlarmUpdateWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val plantManagementService: PlantManagementService by inject(PlantManagementService::class.java)

    override suspend fun doWork(): Result {
        return try {
            plantManagementService.generateUpcomingWaterLogs()
            plantManagementService.syncWaterAlarms()
            plantManagementService.sendDailyForgottenAlarms()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
