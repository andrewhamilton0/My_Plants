package com.example.myplants.di

import com.example.myplants.core.data.DatabaseDriverFactory
import com.example.myplants.core.data.DatabaseDriverFactoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    singleOf(::DatabaseDriverFactoryImpl) bind DatabaseDriverFactory::class
}
