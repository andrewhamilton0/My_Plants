package com.example.myplants.featureplant.domain.waterlog

import com.example.myplants.core.data.UuidProvider
import kotlinx.datetime.LocalDate

data class WaterLog(
    val id: String = UuidProvider.getUuid(),
    val plantId: String,
    val date: LocalDate,
    val isWatered: Boolean
)
