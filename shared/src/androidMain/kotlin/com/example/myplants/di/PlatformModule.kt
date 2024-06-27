package com.example.myplants.di

import com.example.myplants.core.data.DatabaseDriverFactory
import com.example.myplants.core.data.DatabaseDriverFactoryImpl
import com.example.myplants.featureplant.presentation.plant.editplantscreen.EditPlantScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantdetailsscreen.PlantDetailScreenViewModel
import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactoryImpl(get())
    }
    viewModel { PlantListScreenViewModel(get()) }
    viewModel { (plantId: String) -> PlantDetailScreenViewModel(get(), plantId) }
    viewModel { (plantId: String) -> EditPlantScreenViewModel(get(), plantId) }
}
