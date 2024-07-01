package com.example.myplants.di

import com.example.myplants.core.data.DatabaseDriverFactoryImpl
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import com.squareup.sqldelight.db.SqlDriver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactoryImpl(get()).create()
    }
    viewModel { PlantListScreenViewModel(get()) }
    viewModel { (plantId: String, logId: String) -> PlantDetailScreenViewModel(get(), plantId, logId) }
    viewModel { (plantId: String) -> EditPlantScreenViewModel(get(), plantId) }
}
