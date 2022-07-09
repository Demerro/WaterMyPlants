package com.dewerro.watermyplants.presentation.utils

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.MainActivity
import com.dewerro.watermyplants.presentation.utils.NotificationBuilder.Companion.CHANNEL_ID

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        createNotification(context)
    }

    private fun createNotification(context: Context) {
        val color = Color.BLUE

        val builder =
            NotificationCompat.Builder(context, NOTIFICATION_ID_STRING)
                .setSmallIcon(R.drawable.ic_water_drop)
                .setColor(color)
                .setContentText(context.getString(R.string.time_to_water))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setContentIntent(getPendingIntent(context))
                .setChannelId(CHANNEL_ID)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
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
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_ID_STRING = "watering_reminder"
    }
}