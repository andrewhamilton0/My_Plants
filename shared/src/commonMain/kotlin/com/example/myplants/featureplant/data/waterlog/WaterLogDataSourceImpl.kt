package com.example.myplants.featureplant.data.waterlog

import com.example.myplants.PlantDatabase
import com.example.myplants.featureplant.domain.waterlog.WaterLogDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import plantsdb.WaterLogEntity

class WaterLogDataSourceImpl(
    db: PlantDatabase
) : WaterLogDataSource {

    private val queries = db.waterLogEntityQueries

    override fun getAllWaterLogs(): Flow<List<WaterLogEntity>> {
        return queries.getAllWaterLogs().asFlow().mapToList()
    }

    override fun getWaterLogByLogId(logId: String): Flow<WaterLogEntity?> {
        return queries.getWaterLogById(logId).asFlow().mapToOneOrNull()
    }

    override suspend fun upsertWaterLog(waterLog: WaterLogEntity) {
        queries.upsertWaterLog(
            id = waterLog.id,
            plantId = waterLog.plantId,
            date = waterLog.date,
            watered = waterLog.watered
        )
    }

    override suspend fun deleteWaterLog(waterLogId: String) {
        queries.deleteWaterLogById(waterLogId)
    }
}
