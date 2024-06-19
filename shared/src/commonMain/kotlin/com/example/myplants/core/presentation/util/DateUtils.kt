package com.example.myplants.core.presentation.util

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

object DateUtils {
    fun nextOccurrenceOfDay(currentDate: LocalDate, targetDays: Set<DayOfWeek>): LocalDate? {
        if (targetDays.isEmpty()) return null
        var date = currentDate
        while (true) {
            if (date.dayOfWeek in targetDays) return date
            date = date.plus(DatePeriod(days = 1))
        }
    }
    fun getDateDescriptor(currentDate: LocalDate, dateToDescribe: LocalDate): DateDescriptor {
        return when (currentDate) {
            dateToDescribe -> DateDescriptor.Today
            dateToDescribe.plus(DatePeriod(days = 1)) -> DateDescriptor.Tomorrow
            else -> DateDescriptor.Date(dateToDescribe)
        }
    }
}
