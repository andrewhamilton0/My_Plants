package com.example.myplants.featureplant.data.waterlog

import com.example.myplants.PlantDatabase
import com.example.myplants.featureplant.domain.waterlog.WaterLogDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import plantsdb.WaterLogEntity

class WaterLogDataSourceImpl(
    db: PlantDatabase
) : WaterLogDataSource {

    private val queries = db.waterLogEntityQueries

    override fun getAllWaterLogs(): Flow<List<WaterLogEntity>> {
        return queries.getAllWaterLogs().asFlow().mapToList()
    }

    override suspend fun insertWaterLog(waterLog: WaterLogEntity) {
        queries.insertWaterLog(
            id = waterLog.id,
            plantId = waterLog.plantId,
            date = waterLog.date,
            watered = waterLog.watered
        )
    }

    override suspend fun deleteWaterLogsByPlantId(plantId: String) {
        queries.deleteWaterLogByPlantId(plantId)
    }

    override suspend fun deleteWaterLog(waterLogId: String) {
        queries.deleteWaterLogById(waterLogId)
    }
}
