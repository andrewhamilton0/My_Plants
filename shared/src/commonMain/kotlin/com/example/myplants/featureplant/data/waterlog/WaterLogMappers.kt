package com.example.myplants.featureplant.data.waterlog

import com.example.myplants.featureplant.domain.waterlog.WaterLog
import kotlinx.datetime.LocalDate
import plantsdb.WaterLogEntity

fun WaterLog.toWaterLogEntity(): WaterLogEntity {
    return WaterLogEntity(
        id = id,
        plantId = plantId,
        date = date.toEpochDays().toLong(),
        watered = isWatered
    )
}

fun WaterLogEntity.toWaterLog(): WaterLog {
    return WaterLog(
        id = id,
        plantId = plantId,
        date = LocalDate.fromEpochDays(date.toInt()),
        isWatered = watered
    )
}
