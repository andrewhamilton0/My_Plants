package com.example.myplants.di

import com.example.myplants.core.data.DatabaseDriverFactoryImpl
import com.example.myplants.core.presentation.util.LocaleProvider
import com.example.myplants.core.presentation.util.LocaleProviderImpl
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformCoreModule: Module = module {
    single<SqlDriver> {
        DatabaseDriverFactoryImpl().create()
    }
    single<LocaleProvider> { LocaleProviderImpl() }
}
