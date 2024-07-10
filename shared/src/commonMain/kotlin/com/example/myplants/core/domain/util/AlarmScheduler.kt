package com.example.myplants.core.domain.util

interface AlarmScheduler {
    fun setAlarm(triggerAtMillis: Long, requestCode: Int, vararg extra: Pair<String, Any>)
    fun cancelAlarm(requestCode: Int, vararg extra: Pair<String, Any>)
}
