package com.example.myplants.featureplant.presentation.plant.util

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

sealed interface DateDescriptor {
    object Today : DateDescriptor
    object Tomorrow : DateDescriptor
    data class Date(val date: LocalDate) : DateDescriptor

    companion object {
        fun getDateDescriptor(currentDate: LocalDate, dateToDescribe: LocalDate): DateDescriptor {
            return when (dateToDescribe) {
                currentDate -> Today
                currentDate.plus(DatePeriod(days = 1)) -> Tomorrow
                else -> Date(dateToDescribe)
            }
        }
    }
}
