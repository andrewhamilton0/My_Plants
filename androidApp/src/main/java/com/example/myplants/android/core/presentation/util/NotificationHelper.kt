package com.example.myplants.android.core.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myplants.android.MainActivity
import com.example.myplants.android.R

class NotificationHelper(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotificationChannel(
        name: String,
        channelId: String,
        descriptionText: String? = null,
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, importance).apply {
                descriptionText?.let { description = it }
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        channelId: String,
        title: String,
        message: String? = null,
        notificationId: Int,
        largeIcon: Bitmap? = null,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        autoCancel: Boolean = true
    ) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_single_plant)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(priority)
            .setLargeIcon(largeIcon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(autoCancel)

        val notification = builder.build()
        notificationManager.notify(notificationId, notification)
    }
}
