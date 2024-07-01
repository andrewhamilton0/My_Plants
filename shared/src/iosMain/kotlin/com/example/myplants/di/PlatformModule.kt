package com.example.myplants.di

import com.example.myplants.core.data.DatabaseDriverFactoryImpl
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactoryImpl().create()
    }
    factory { PlantListScreenViewModel(get()) }
    factory { (plantId: String, logId: String) -> PlantDetailScreenViewModel(get(), plantId, logId) }
    factory { (plantId: String) -> EditPlantScreenViewModel(get(), plantId) }
}
