package com.dewerro.watermyplants.presentation.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.MainActivity

class NotificationBuilder {
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.watering_reminder),
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = CHANNEL_DESCRIPTION }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(context: Context, message: String): Notification {
        val color = Color.CYAN

        val builder =
            NotificationCompat.Builder(context, NOTIFICATION_ID_STRING)
                .setSmallIcon(R.drawable.ic_water_drop)
                .setColor(color)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setContentIntent(getPendingIntent(context))
                .setChannelId(CHANNEL_ID)

        return builder.build()
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, 0)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 0
        const val NOTIFICATION_ID_STRING = "watering_reminder"

        const val CHANNEL_ID = "watermyplants_channel_0"
        const val CHANNEL_DESCRIPTION = "Sends watering reminders"
    }
}