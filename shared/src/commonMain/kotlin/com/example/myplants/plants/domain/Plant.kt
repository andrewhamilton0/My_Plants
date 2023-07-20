package com.example.myplants.plants.domain

data class Plant(
    val id: String,
    val name: String,
    val description: String,
    val waterAmount: String,
    val waterDays: List<String>,
    val waterTime: String,
    val isWatered: Boolean,
    val plantSize: String,
    val photoKey: String?
)
