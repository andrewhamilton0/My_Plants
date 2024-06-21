package com.example.myplants.android.plant.presentation.util

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    object PlantList

    @Serializable
    data class PlantDetail(
        val plantId: String? = null
    )

    @Serializable
    object Notification
}
