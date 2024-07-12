package com.example.myplants.di

import com.example.myplants.PlantDatabase
import com.example.myplants.core.data.db.setOfDaysOfWeekAdapter
import com.example.myplants.featureplant.data.PlantManagementServiceImpl
import com.example.myplants.featureplant.data.plant.PlantDataSourceImpl
import com.example.myplants.featureplant.data.plant.PlantRepositoryImpl
import com.example.myplants.featureplant.data.waterlog.WaterLogDataSourceImpl
import com.example.myplants.featureplant.data.waterlog.WaterLogRepositoryImpl
import com.example.myplants.featureplant.domain.PlantManagementService
import com.example.myplants.featureplant.domain.plant.PlantDataSource
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.waterlog.WaterLogDataSource
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import plantsdb.PlantEntity

private val commonCoreModule = module {
    single<PlantDatabase> {
        PlantDatabase(
            driver = get(),
            plantEntityAdapter = PlantEntity.Adapter(
                waterDaysAdapter = setOfDaysOfWeekAdapter
            )
        )
    }
    single<PlantDataSource> {
        PlantDataSourceImpl(get())
    }
    single<WaterLogDataSource> {
        WaterLogDataSourceImpl(get())
    }
    singleOf(::PlantRepositoryImpl) bind PlantRepository::class
    singleOf(::WaterLogRepositoryImpl) bind WaterLogRepository::class
    single<PlantManagementService> {
        PlantManagementServiceImpl(
            plantRepository = get(),
            waterLogRepository = get(),
            alarmScheduler = get(named("plant_alarm_scheduler"))
        )
    }
}

val coreModule: Module = module {
    includes(platformCoreModule + commonCoreModule)
}
