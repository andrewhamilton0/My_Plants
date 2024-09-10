package com.example.myplants.featureplant.domain.waterlog

import kotlinx.coroutines.flow.Flow
import plantsdb.WaterLogEntity

interface WaterLogDataSource {
    fun getAllWaterLogs(): Flow<List<WaterLogEntity>>
    fun getWaterLogByLogId(logId: String): Flow<WaterLogEntity?>
    suspend fun upsertWaterLog(waterLog: WaterLogEntity)
    suspend fun deleteWaterLog(waterLogId: String)
    suspend fun deleteWaterLogsOfPlantId(plantId: String)
}
