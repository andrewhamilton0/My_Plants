package com.example.myplants

import com.example.myplants.di.coreModule
import org.koin.core.context.startKoin

class Helper() {
    fun initKoin() {
        startKoin {
            modules(coreModule)
        }
    }
}
