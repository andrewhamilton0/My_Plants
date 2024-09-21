package com.example.myplants.featureplant.data.waterlog

import com.example.myplants.featureplant.domain.waterlog.WaterLog
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakeWaterLogRepositoryImpl : WaterLogRepository {

    private val _waterLogs = MutableStateFlow<List<WaterLog>>(emptyList())
    private val waterLogs = _waterLogs.asStateFlow()

    override fun getAllWaterLogs(): Flow<List<WaterLog>> {
        return waterLogs
    }

    override fun getWaterLog(waterLogId: String): Flow<WaterLog?> {
        return waterLogs.map { logs ->
            logs.find { it.id == waterLogId }
        }
    }

    override suspend fun upsertWaterLog(waterLog: WaterLog) {
        val currentLogs = _waterLogs.value.toMutableList()
        currentLogs.removeAll { it.id == waterLog.id }
        currentLogs.add(waterLog)
        _waterLogs.value = currentLogs
    }

    override suspend fun deleteWaterLog(waterLogId: String) {
        val currentLogs = _waterLogs.value.toMutableList()
        currentLogs.removeAll { it.id == waterLogId }
        _waterLogs.value = currentLogs
    }

    override suspend fun deleteAllWaterLogOfPlant(plantId: String) {
        Unit
    }

    override suspend fun toggleWater(waterLogId: String) {
        val currentLogs = _waterLogs.value.toMutableList()
        val waterLog = currentLogs.find { it.id == waterLogId }
        val updatedWaterLog = waterLog?.copy(isWatered = !waterLog.isWatered)
        updatedWaterLog?.let { log ->
            currentLogs.removeAll { it.id == log.id }
            currentLogs.add(updatedWaterLog)
            _waterLogs.value = currentLogs
        }
    }
}
