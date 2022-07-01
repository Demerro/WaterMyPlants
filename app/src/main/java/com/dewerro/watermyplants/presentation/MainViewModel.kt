package com.dewerro.watermyplants.presentation

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModel
import com.dewerro.watermyplants.presentation.utils.AlarmReceiver
import com.dewerro.watermyplants.presentation.utils.NotificationChannelInfo

class MainViewModel : ViewModel() {
    fun setAlarm(context: Context) {
        createNotificationChannel(context)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmInfo = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 60000,
            getPendingIntent(context)
        )
        alarmManager.setAlarmClock(alarmInfo, getPendingIntent(context))
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationChannelInfo.CHANNEL_ID,
                NotificationChannelInfo.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = NotificationChannelInfo.CHANNEL_DESCRIPTION }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }
}