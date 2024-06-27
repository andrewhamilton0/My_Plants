package com.example.myplants.featureplant.presentation.plant.plantlistscreen.util

import com.example.myplants.featureplant.presentation.plant.util.DateDescriptor
import kotlinx.datetime.LocalDateTime

sealed class WaterStatus(
    open val localDateTime: LocalDateTime,
    open val dateDescriptor: DateDescriptor
) {
    data class Upcoming(
        override val localDateTime: LocalDateTime,
        override val dateDescriptor: DateDescriptor
    ) : WaterStatus(localDateTime, dateDescriptor)
    data class Forgotten(
        override val localDateTime: LocalDateTime,
        override val dateDescriptor: DateDescriptor
    ) : WaterStatus(localDateTime, dateDescriptor)
}
