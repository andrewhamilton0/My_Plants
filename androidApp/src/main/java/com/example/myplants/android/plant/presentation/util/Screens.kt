package com.example.myplants.android.plant.presentation.util

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    object PlantList

    @Serializable
    data class PlantDetail(
        val plantId: String
    )

    @Serializable
    object Notification

    @Serializable
    data class EditPlant(
        val plantId: String? = null
    )
}
