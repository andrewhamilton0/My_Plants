package com.example.myplants.android.core

import android.app.Application
import com.example.myplants.android.plant.data.workmanager.PlantWorkerManager
import com.example.myplants.android.plant.presentation.notification.PlantNotification
import com.example.myplants.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(coreModule)
        }
        val plantWorkerManager = PlantWorkerManager(this)
        val plantNotification = PlantNotification(this)
        plantNotification.createNotificationChannels()
        plantWorkerManager.start()
    }
}
