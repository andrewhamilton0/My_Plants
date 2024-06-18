package com.example.myplants.core.presentation.util

import kotlinx.datetime.LocalDate

sealed interface DateDescriptor {
    object Today : DateDescriptor
    object Tomorrow : DateDescriptor
    data class Date(val date: LocalDate) : DateDescriptor
}
