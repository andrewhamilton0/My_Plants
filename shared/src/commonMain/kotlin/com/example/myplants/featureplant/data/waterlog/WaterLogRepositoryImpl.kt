package com.example.myplants.featureplant.data.waterlog

import com.example.myplants.featureplant.domain.waterlog.WaterLog
import com.example.myplants.featureplant.domain.waterlog.WaterLogDataSource
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest

class WaterLogRepositoryImpl(
    private val waterLogDataSource: WaterLogDataSource
) : WaterLogRepository {

    override fun getAllWaterLogs(): Flow<List<WaterLog>> {
        return waterLogDataSource.getAllWaterLogs().mapLatest {
            it.map { logEntity ->
                logEntity.toWaterLog()
            }
        }
    }

    override suspend fun upsertWaterLog(waterLog: WaterLog) {
        waterLogDataSource.insertWaterLog(waterLog.toWaterLogEntity())
    }

    override suspend fun deleteWaterLog(waterLogId: String) {
        waterLogDataSource.deleteWaterLog(waterLogId)
    }

    override suspend fun toggleWater(waterLogId: String) {
        val log = getWaterLogById(waterLogId)
        log?.let {
            upsertWaterLog(it.copy(isWatered = !it.isWatered))
        }
    }

    private suspend fun getWaterLogById(waterLogId: String): WaterLog? {
        return waterLogDataSource.getWaterLogByLogId(waterLogId).first()?.toWaterLog()
    }
}
