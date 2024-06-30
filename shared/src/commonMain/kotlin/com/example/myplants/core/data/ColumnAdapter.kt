package com.example.myplants.core.data

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber

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
