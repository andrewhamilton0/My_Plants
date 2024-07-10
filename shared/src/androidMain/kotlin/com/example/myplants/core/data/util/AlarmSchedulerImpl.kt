package com.example.myplants.core.data.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.example.myplants.core.domain.util.AlarmScheduler

class AlarmSchedulerImpl(
    private val context: Context,
    private val receiverClassName: String
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun setAlarm(triggerAtMillis: Long, requestCode: Int, vararg extra: Pair<String, Any>) {
        val pendingIntent = getPendingIntent(requestCode, *extra)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }

    override fun cancelAlarm(requestCode: Int, vararg extra: Pair<String, Any>) {
        val pendingIntent = getPendingIntent(requestCode, *extra)
        alarmManager.cancel(pendingIntent)
    }

    private fun getPendingIntent(requestCode: Int, vararg extra: Pair<String, Any>): PendingIntent {
        val intent = Intent(context, Class.forName(receiverClassName)).putExtras(bundleOf(*extra))
        return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}
