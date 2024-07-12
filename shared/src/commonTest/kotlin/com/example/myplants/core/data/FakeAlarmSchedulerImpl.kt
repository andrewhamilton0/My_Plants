package com.example.myplants.core.data

import com.example.myplants.core.domain.util.AlarmScheduler

class FakeAlarmSchedulerImpl : AlarmScheduler {
    override fun setAlarm(
        triggerAtMillis: Long,
        requestCode: Int,
        vararg extra: Pair<String, Any>
    ) {
        Unit // SetsAlarm
    }

    override fun cancelAlarm(requestCode: Int, vararg extra: Pair<String, Any>) {
        Unit // Cancels Alarm
    }
}
