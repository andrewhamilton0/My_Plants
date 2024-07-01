package com.example.myplants.featureplant.data

import com.example.myplants.featureplant.data.plant.FakePlantRepositoryImpl
import com.example.myplants.featureplant.data.waterlog.FakeWaterLogRepositoryImpl
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class PlantManagementServiceImplTest() {

    @Test
    fun `test get upcoming plants`() = runBlocking {
        val plantRepository: PlantRepository = FakePlantRepositoryImpl()
        val waterLogRepository: WaterLogRepository = FakeWaterLogRepositoryImpl()
        //TODO FIX DATE IN TEST
        val date = Flow(LocalDate(2023, 1, 1))
        val service = PlantManagementServiceImpl(plantRepository, waterLogRepository, date)

        val tomato = Plant(
            id = "1A",
            name= "Tomato",
            description = "Tomato plant",
            waterAmount = "100ml",
            waterDays = setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
            waterTime = LocalTime(12, 0),
            plantSize = PlantSize.MEDIUM,
            photo = null
        )
        val grape = Plant(
            id = "2b",
            name= "Grapes",
            description = "Grape vine",
            waterAmount = "50ml",
            waterDays = setOf(DayOfWeek.THURSDAY),
            waterTime = LocalTime(1, 20),
            plantSize = PlantSize.SMALL,
            photo = null
        )
        service.upsertPlant(tomato)
        service.upsertPlant(grape)

        val upcomingPlants = service.getUpcomingPlants().first()
        val tomatoWaterLogPair = upcomingPlants.find { it.plant.id == "1A" }
        val grapesWaterLogPair = upcomingPlants.find { it.plant.id == "2b" }

        //assertEquals()
    }

}