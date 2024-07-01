package com.example.myplants.di

import com.example.myplants.featureplant.presentation.plant.plantlistscreen.PlantListScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModelProvider : KoinComponent {
    fun getPlantListScreenViewModel() = get<PlantListScreenViewModel>()
}
