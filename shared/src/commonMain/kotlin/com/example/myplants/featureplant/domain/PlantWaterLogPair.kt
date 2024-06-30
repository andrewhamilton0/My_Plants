package com.example.myplants.featureplant.domain

import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.waterlog.WaterLog

data class PlantWaterLogPair(
    val plant: Plant,
    val waterLog: WaterLog,
    val uiDate: String
)
