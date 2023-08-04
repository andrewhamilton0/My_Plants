package com.example.myplants.di

import com.example.myplants.PlantDatabase
import com.example.myplants.core.data.listOfStringsAdapter
import com.example.myplants.plants.data.PlantDataSourceImpl
import com.example.myplants.plants.data.PlantRepositoryImpl
import com.example.myplants.plants.domain.PlantDataSource
import com.example.myplants.plants.domain.PlantRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import plantsdb.PlantEntity

private val commonCoreModule = module {
    single<PlantDataSource> {
        PlantDataSourceImpl(
            PlantDatabase(
                driver = get(),
                plantEntityAdapter = PlantEntity.Adapter(
                    waterDaysAdapter = listOfStringsAdapter
                )
            )
        )
    }
    singleOf(::PlantRepositoryImpl) bind PlantRepository::class
}

val coreModule: Module get() = module {
    includes(commonCoreModule + platformCoreModule)
}
