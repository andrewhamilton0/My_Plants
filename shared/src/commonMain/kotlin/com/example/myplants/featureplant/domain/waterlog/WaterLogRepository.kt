package com.example.myplants.featureplant.domain.waterlog

import kotlinx.coroutines.flow.Flow

interface WaterLogRepository {
    fun getAllWaterLogs(): Flow<List<WaterLog>>
    fun getWaterLog(waterLogId: String): Flow<WaterLog?>
    suspend fun upsertWaterLog(waterLog: WaterLog)
    suspend fun deleteWaterLog(waterLogId: String)
    suspend fun deleteAllWaterLogOfPlant(plantId: String)
    suspend fun toggleWater(waterLogId: String)
}
