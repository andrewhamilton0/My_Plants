package com.example.myplants.featureplant.data

import com.example.myplants.featureplant.data.plant.FakePlantRepositoryImpl
import com.example.myplants.featureplant.data.waterlog.FakeWaterLogRepositoryImpl
import com.example.myplants.featureplant.domain.PlantWaterLogPair
import com.example.myplants.featureplant.domain.plant.Plant
import com.example.myplants.featureplant.domain.plant.PlantRepository
import com.example.myplants.featureplant.domain.plant.PlantSize
import com.example.myplants.featureplant.domain.waterlog.WaterLog
import com.example.myplants.featureplant.domain.waterlog.WaterLogRepository
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertEquals

class PlantManagementServiceImplTest() {

    @Test
    fun `test get upcoming plants`() = runBlocking {
        val plantRepository: PlantRepository = FakePlantRepositoryImpl()
        val waterLogRepository: WaterLogRepository = FakeWaterLogRepositoryImpl()
        val date = MutableStateFlow(LocalDate(2024, 1, 1))
        val service = PlantManagementServiceImpl(plantRepository, waterLogRepository, date)
        val upcoming = service.getUpcomingPlants()
        val job = launch { upcoming.collect { } }

        val tomato = createPlant("1A", "Tomato", setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))
        val grape = createPlant("2B", "Grape", setOf(DayOfWeek.THURSDAY))
        service.upsertPlant(tomato)
        simulateDateChange(date, LocalDate(2024, 1, 8))
        service.upsertPlant(grape)
        simulateDateChange(date, LocalDate(2024, 1, 15))
        upcoming.first().forEach { if (it.plant.id == "1A") service.toggleWater(it.waterLog.id) }
        simulateDateChange(date, LocalDate(2024, 1, 16))

        val expectedPairs = listOf(
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 16), true)),
            PlantWaterLogPair(tomato, WaterLog("1", "2B", LocalDate(2024, 1, 18), false)),
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 22), false))
        )
        val finalUpcoming = upcoming.first().sortedBy { it.waterLog.date }
            .map { PlantWaterLogPair(it.plant, it.waterLog.copy(id = "1")) }

        assertEquals(expectedPairs.size, finalUpcoming.size)
        expectedPairs.forEachIndexed { index, expected ->
            val actual = finalUpcoming[index]
            assertEquals(expected, actual)
        }

        job.cancelAndJoin()
    }

    @Test
    fun `test get history`() = runBlocking {
        val plantRepository: PlantRepository = FakePlantRepositoryImpl()
        val waterLogRepository: WaterLogRepository = FakeWaterLogRepositoryImpl()
        val date = MutableStateFlow(LocalDate(2024, 1, 1))
        val service = PlantManagementServiceImpl(plantRepository, waterLogRepository, date)
        val history = service.getHistory()
        val job = launch { history.collect { } }

        val tomato = createPlant("1A", "Tomato", setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))
        val grape = createPlant("2B", "Grape", setOf(DayOfWeek.THURSDAY))
        service.upsertPlant(tomato)
        simulateDateChange(date, LocalDate(2024, 1, 2))
        history.first().forEach { if (it.plant.id == "1A") service.toggleWater(it.waterLog.id) }
        simulateDateChange(date, LocalDate(2024, 1, 8))
        service.upsertPlant(grape)
        simulateDateChange(date, LocalDate(2024, 1, 16))

        val expectedPairs = listOf(
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 1), true)),
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 2), false)),
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 8), false)),
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 9), false)),
            PlantWaterLogPair(grape, WaterLog("1", "2B", LocalDate(2024, 1, 11), false)),
            PlantWaterLogPair(tomato, WaterLog("1", "1A", LocalDate(2024, 1, 15), false))
        )
        val finalHistory = history.first().sortedBy { it.waterLog.date }
            .map { PlantWaterLogPair(it.plant, it.waterLog.copy(id = "1")) }

        assertEquals(expectedPairs.size, finalHistory.size)
        expectedPairs.forEachIndexed { index, expected ->
            val actual = finalHistory[index]
            assertEquals(expected, actual)
        }

        job.cancelAndJoin()
    }

    private suspend fun simulateDateChange(start: MutableStateFlow<LocalDate>, endDate: LocalDate) {
        while (start.value < endDate) {
            delay(10)
            start.update { start.value.plus(1, DateTimeUnit.DAY) }
        }
    }

    private fun createPlant(id: String, name: String, waterDays: Set<DayOfWeek>): Plant {
        return Plant(
            id = id,
            name = name,
            description = "$name plant",
            waterAmount = "100ml",
            waterDays = waterDays,
            waterTime = LocalTime(12, 0),
            plantSize = PlantSize.MEDIUM,
            photo = null
        )
    }
}
