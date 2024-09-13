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

    override fun getWaterLog(waterLogId: String): Flow<WaterLog?> {
        return waterLogDataSource.getWaterLogByLogId(waterLogId).mapLatest { it?.toWaterLog() }
    }

    override suspend fun upsertWaterLog(waterLog: WaterLog) {
        waterLogDataSource.upsertWaterLog(waterLog.toWaterLogEntity())
    }

    override suspend fun deleteWaterLog(waterLogId: String) {
        waterLogDataSource.deleteWaterLog(waterLogId)
    }

    override suspend fun deleteAllWaterLogOfPlant(plantId: String) {
        waterLogDataSource.deleteWaterLogsOfPlantId(plantId)
    }

    override suspend fun toggleWater(waterLogId: String) {
        val log = getWaterLog(waterLogId).first()
        log?.let {
            upsertWaterLog(it.copy(isWatered = !it.isWatered))
        }
    }
}
