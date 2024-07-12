package com.example.myplants.di

import com.example.myplants.core.data.db.DatabaseDriverFactoryImpl
import com.example.myplants.core.data.util.AlarmSchedulerImpl
import com.example.myplants.core.data.util.PhotoStorageManager
import com.example.myplants.core.domain.util.AlarmScheduler
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactoryImpl(get()).create()
    }
    single { PhotoStorageManager(get(), Dispatchers.IO) }
    single<AlarmScheduler>(named("plant_alarm_scheduler")) {
        AlarmSchedulerImpl(get(), "com.example.myplants.android.plant.presentation.notification.PlantNotificationAlarmReceiver")
    }
    viewModel { PlantListScreenViewModel(get()) }
    viewModel { (plantId: String, logId: String) -> PlantDetailScreenViewModel(get(), plantId, logId) }
    viewModel { (plantId: String) -> EditPlantScreenViewModel(get(), plantId) }
}
