package com.example.myplants.featureplant.domain.waterlog

import kotlinx.coroutines.flow.Flow
import plantsdb.WaterLogEntity

interface WaterLogDataSource {
    fun getAllWaterLogs(): Flow<List<WaterLogEntity>>
    suspend fun insertWaterLog(waterLog: WaterLogEntity)
    suspend fun deleteWaterLogsByPlantId(plantId: String)
    suspend fun deleteWaterLog(waterLogId: String)
}
