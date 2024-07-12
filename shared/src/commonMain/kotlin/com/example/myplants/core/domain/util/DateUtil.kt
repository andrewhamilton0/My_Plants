package com.example.myplants.core.domain.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object DateUtil {
    fun nextOccurrenceOfDay(startDate: LocalDate, targetDays: Set<DayOfWeek>): LocalDate? {
        // Includes Today
        if (targetDays.isEmpty()) return null
        var date = startDate
        while (true) {
            if (date.dayOfWeek in targetDays) return date
            date = date.plus(DatePeriod(days = 1))
        }
    }

    fun previousOccurrenceOfDay(startDate: LocalDate, targetDays: Set<DayOfWeek>): LocalDate? {
        // Doesn't Include Today
        if (targetDays.isEmpty()) return null
        var date = startDate.minus(DatePeriod(days = 1))
        while (true) {
            if (date.dayOfWeek in targetDays) return date
            date = date.minus(DatePeriod(days = 1))
        }
    }

    fun getCurrentDateTime(): Flow<LocalDateTime> {
        return flow<LocalDateTime> {
            emit(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
            val currentInstant = Clock.System.now()
            val nextMinuteInstant = currentInstant.plus(1, DateTimeUnit.MINUTE)
            val initialDelayMillis =
                nextMinuteInstant.toEpochMilliseconds() - currentInstant.toEpochMilliseconds()

            delay(initialDelayMillis)
            while (true) {
                emit(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
                delay(1000L * 60)
            }
        }
    }
}
