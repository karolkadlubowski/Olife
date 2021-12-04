package com.example.olife.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.olife.CalendarFragment
import com.example.olife.MainActivity
import com.example.olife.R
import com.example.olife.data.model.Event
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

const val notificationId = 1
const val channelID = "com.example.olife.channel1"
const val titleExtra = "com.example.olife.titleExtra"
const val messageExtra = "com.example.olife.messageExtra"
const val eventID = "com.example.olife.eventID"

class EventNotification : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val notification: Notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(intent.getIntExtra(eventID,0), notification)
    }
//
//    fun onCancel(){
//        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.notify(intent.getIntExtra(eventID,0), notification)
//    }
}

object EventNotificationUtils {


    init {
        //createEventNotificationChannel()
    }

   // private val channelID = "com.example.olife.channel1"
    private var notificationManager: NotificationManager? = null
    private  var calendarUtils = CalendarUtils
    private var timeUtils = TimeUtils

    fun createEventNotificationChannel(context: Context){//id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelID, titleExtra, importance).apply {
                description = messageExtra
            }
            notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }
/*
    public fun displayNotification(context: Context) {
        val tapResultIntent = Intent(context, CalendarFragment::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, channelID)
            .setContentTitle(mEvent.name)
            .setContentText("Event is planned on " + calendarUtils.getStringFromLocalDate(mEvent!!.eventDate!!))
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(mEvent!!.notificationDate!!, mEvent!!.notificationTime!!)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

        notificationManager?.notify(1,notification)
    }
*/

    fun deleteNotification(context: Context,mEvent:Event){
        val intent = Intent(context, EventNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            mEvent.id!!,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

fun scheduleNotification(context: Context,mEvent:Event) {
        val intent = Intent(context, EventNotification::class.java)
        val title = "Something is going on just right now -> check it out!"
        val message =
            if(mEvent.name.isNullOrBlank()) {"Event"} else {mEvent?.name } + " is planned on " + calendarUtils.getStringFromLocalDate(mEvent!!.notificationDate!!) + " at " + timeUtils.getStringFromLocalTime(
                mEvent!!.notificationTime!!
            )
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)
        intent.putExtra(eventID,mEvent.id)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            mEvent.id!!,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(mEvent!!.notificationDate!!, mEvent!!.notificationTime!!)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun getTime(localDate: LocalDate, localTime: LocalTime): Long {
        val calendar = Calendar.getInstance()
        //calendar.set(2021,12,1,23,10)
        calendar.set(
            localDate.year,
            localDate.monthValue - 1,
            localDate.dayOfMonth,
            localTime.hour,
            localTime.minute,
            1
        )
        return calendar.timeInMillis
    }


}