package com.example.olife.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.olife.AlarmOnActivity
import com.example.olife.R
import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import java.time.LocalTime
import java.util.*
import android.text.format.DateUtils


const val alarmChannelID = "com.example.olife.channelAlarm"
const val alarmTitleExtra = "com.example.olife.titleExtraAlarm"
const val alarmMessageExtra = "com.example.olife.messageExtraAlarm"
const val alarmID = "com.example.olife.alarmID"
const val alarmCredentials = "alarmCredentials"

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        /* var i = Intent(context, AlarmOnActivity::class.java)
         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         context?.startActivity(i)*/  //Launching new activity not working?


        val tapResultIntent = Intent(context, AlarmOnActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification = NotificationCompat.Builder(context, alarmChannelID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle(intent.getStringExtra(alarmTitleExtra))
            .setContentText(intent.getStringExtra(alarmMessageExtra))
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(intent.getIntExtra(alarmID, 0), notification)

        var alarmUtils = AlarmUtils
        var mAlarm = intent.getSerializableExtra(alarmCredentials) as Alarm

        if (mAlarm.soundMemoryLocation.isNullOrBlank())
            alarmUtils.mediaPlayer = MediaPlayer.create(context, R.raw.ringtone_default)
        else
            alarmUtils.mediaPlayer = MediaPlayer.create(context, R.raw.ringtone_default)

        if (mAlarm.vibration!!) {
            alarmUtils.vibe = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            var pattern = longArrayOf(0, 200, 0)
            alarmUtils.vibe.vibrate(pattern, 0)
        } else {
            alarmUtils.vibe = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            var pattern = longArrayOf(0, 200, 0)
            alarmUtils.vibe.vibrate(pattern, 0)
        }

        if (mAlarm.repeat!!) {
            alarmUtils.createAlarmNotificationChannel(context!!)
            alarmUtils.scheduleAlarm(context!!, mAlarm!!)
        }


        alarmUtils.mediaPlayer.start()

    }
}

object AlarmUtils {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var vibe: Vibrator


    private var notificationManager: NotificationManager? = null

    fun createAlarmNotificationChannel(context: Context) {//id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(alarmChannelID, alarmTitleExtra, importance).apply {
                description = alarmMessageExtra
            }
            channel.setSound(null, null)
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun scheduleAlarm(context: Context, mAlarm: Alarm) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val title = "Something is going on just right now -> check it out!"
        val message = "Tap to turn off the alarm"

        intent.putExtra(alarmTitleExtra, title)
        intent.putExtra(alarmMessageExtra, message)
        intent.putExtra(alarmID, mAlarm.id)
        intent.putExtra(alarmCredentials, mAlarm)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            mAlarm.id!!,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(mAlarm.time!!)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC,
            time,
            pendingIntent
        )
    }

    private fun getTime(localTime: LocalTime): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, localTime.hour)
        calendar.set(Calendar.MINUTE, localTime.minute)
        calendar.set(Calendar.SECOND, 0)
        if (calendar.before(Calendar.getInstance()))
            calendar.add(Calendar.DAY_OF_MONTH, 1)

        return calendar.timeInMillis
    }

    fun cancelAlarm(context: Context, mAlarm: Alarm) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            mAlarm.id!!,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}

