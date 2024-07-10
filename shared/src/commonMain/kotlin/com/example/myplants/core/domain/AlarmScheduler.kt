package com.example.myplants.core.domain

interface AlarmScheduler {
    fun setAlarm(triggerAtMillis: Long, title: String, message: String)
}
