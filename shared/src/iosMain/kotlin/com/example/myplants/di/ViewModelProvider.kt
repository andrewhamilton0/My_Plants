package com.example.myplants.di

import com.example.myplants.plants.presentation.plantlistscreen.PlantListScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModelProvider : KoinComponent {
    fun getPlantListScreenViewModel() = get<PlantListScreenViewModel>()
}
