package com.example.myplants.core.data

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber

val setOfLocalDatesAdapter = object : ColumnAdapter<Set<LocalDate>, String> {
    override fun decode(databaseValue: String) =
        if (databaseValue.isEmpty()) {
            setOf()
        } else {
            databaseValue.split(",").map { LocalDate.fromEpochDays(it.toInt()) }.toSet()
        }
    override fun encode(value: Set<LocalDate>) =
        value.map { it.toEpochDays() }.joinToString(separator = ",")
}

val setOfDaysOfWeekAdapter = object : ColumnAdapter<Set<DayOfWeek>, String> {
    override fun decode(databaseValue: String): Set<DayOfWeek> {
        return if (databaseValue.isEmpty()) {
            setOf()
        } else {
            databaseValue.split(",").map { DayOfWeek(it.toInt()) }.toSet()
        }
    }

    override fun encode(value: Set<DayOfWeek>): String {
        return value.map { it.isoDayNumber }.joinToString(separator = ",")
    }
}
