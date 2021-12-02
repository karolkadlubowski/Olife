package com.example.olife

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.navigation.fragment.navArgs
import com.example.olife.data.model.Event
import com.example.olife.databinding.FragmentEventBinding
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.viewmodel.event.EventsViewModel
import com.example.olife.utils.*
import com.example.olife.utils.Notification
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class EventFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var fragmentEventBinding: FragmentEventBinding
    private val calendarUtils = CalendarUtils
    private val timeUtils = TimeUtils
    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var savedDate: LocalDate = LocalDate.now()
    private var savedTime: LocalTime = LocalTime.now()

    private var saveToEvent: Boolean? = null

    private lateinit var eventsViewModel: EventsViewModel

    private var mEvent: Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentEventBinding = FragmentEventBinding.bind(view)
        eventsViewModel = (activity as MainActivity).eventsViewModel

        val args: EventFragmentArgs by navArgs()//HERE CREATE ARGUMENT IN XML
        mEvent = args.selectedEvent

        if (mEvent == null)
            mEvent = Event(
                null,
                null,
                calendarUtils.selectedDate,
                timeUtils.getLocalTimeFromString("08:00"),
                calendarUtils.selectedDate.minusDays(1),
                timeUtils.getLocalTimeFromString("08:00"),
                null
            )
        else {
            if (mEvent!!.name != null)
                fragmentEventBinding.efEtName.setText(mEvent!!.name)
            if (mEvent!!.description != null)
                fragmentEventBinding.efEtDescription.setText(mEvent!!.description)
        }

        fragmentEventBinding.efEtEventDate.setText(mEvent?.eventDate?.let {
            calendarUtils.getStringFromLocalDate(
                it
            )
        })
        fragmentEventBinding.efEtEventTime.setText(mEvent?.eventTime?.let {
            timeUtils.getStringFromLocalTime(
                it
            )
        })
        fragmentEventBinding.efEtNotificationDate.setText(mEvent?.notificationDate?.let {
            calendarUtils.getStringFromLocalDate(
                it
            )
        })
        fragmentEventBinding.efEtNotificationTime.setText(mEvent?.notificationTime?.let {
            timeUtils.getStringFromLocalTime(
                it
            )
        })

        pickDateOnClickListener()
        pickHourOnClickListener()

        fragmentEventBinding.efIbConfirm.setOnClickListener {
            mEvent!!.name = fragmentEventBinding.efEtName.text.toString()
            mEvent!!.description = fragmentEventBinding.efEtDescription.text.toString()
            if(mEvent!!.id==null) {
                eventsViewModel.saveEvent(mEvent!!)
                scheduleNotification()
            }
            else
                eventsViewModel.updateEvent(mEvent!!)
            activity?.onBackPressed()
        }


        fragmentEventBinding.efIbCancel.setOnClickListener {
            activity?.onBackPressed()
        }

       createNotificationChannel()

    }


    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID,name,importance)
        channel.description = desc
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun scheduleNotification(){
        val intent = Intent(context, Notification::class.java)
        val title = "Mordo"
        val message = "Daj mnie cos do zarcia"
        intent.putExtra(titleExtra,title)
        intent.putExtra(messageExtra,message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
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

        showAlert(time,title,message)
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = DateFormat.getLongDateFormat(context)
        val timeFormat = DateFormat.getTimeFormat(context)

        AlertDialog.Builder(context)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: "+ dateFormat.format(date) + " " +timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ -> }
            .show()
    }


    private fun getTime(localDate: LocalDate, localTime: LocalTime):Long{
        val calendar = Calendar.getInstance()
        //calendar.set(2021,12,1,23,10)
        calendar.set(localDate.year,localDate.monthValue-1,localDate.dayOfMonth,localTime.hour,localTime.minute,1)
        return calendar.timeInMillis
    }


    private fun getEventDateCalendar() {
        day = mEvent!!.eventDate?.dayOfMonth!!
        month =mEvent!!.eventDate?.month?.value!!-1
        year = mEvent!!.eventDate?.year!!
    }

    private fun getNotificationDateCalendar() {
        day = mEvent!!.notificationDate?.dayOfMonth!!
        month = mEvent!!.notificationDate?.month?.value!!-1
        year = mEvent!!.notificationDate?.year!!

    }



    private fun getEventTime() {
        hour = mEvent!!.eventTime?.hour!!//cal.get(Calendar.HOUR)
        minute = mEvent!!.eventTime?.minute!! //cal.get(Calendar.MINUTE)
    }

    private fun getNotificationTime() {
        hour = mEvent!!.notificationTime?.hour!!
        minute = mEvent!!.notificationTime?.minute!!
    }

    private fun pickDateOnClickListener() {

        fragmentEventBinding.efEtEventDate.setOnClickListener {
            saveToEvent = true
            getEventDateCalendar()
            parentFragment?.context?.let { DatePickerDialog(it, this, year, month, day).show() }

        }

        fragmentEventBinding.efEtNotificationDate.setOnClickListener {
            getNotificationDateCalendar()
            parentFragment?.context?.let { DatePickerDialog(it, this, year, month, day).show() }

        }
    }

    private fun pickHourOnClickListener() {
        fragmentEventBinding.efEtEventTime.setOnClickListener {
            saveToEvent = true
            getEventTime()
            TimePickerDialog(parentFragment?.context,android.R.style.Theme_Holo_Dialog ,this, hour, minute, true).show()

        }

        fragmentEventBinding.efEtNotificationTime.setOnClickListener {
            getNotificationTime()
            TimePickerDialog(parentFragment?.context,android.R.style.Theme_Holo_Dialog, this, hour, minute, true).show()


        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDate = LocalDate.of(year, month+1, dayOfMonth)
        if (saveToEvent == true) {
            fragmentEventBinding.efEtEventDate.setText(
                calendarUtils.getStringFromLocalDate(
                    savedDate
                )
            )
            mEvent?.eventDate = savedDate
            saveToEvent = false
        } else
            fragmentEventBinding.efEtNotificationDate.setText(
                calendarUtils.getStringFromLocalDate(
                    savedDate
                )
            )
        mEvent?.notificationDate = savedDate
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedTime = LocalTime.of(hourOfDay, minute)

        if (saveToEvent == true) {
            fragmentEventBinding.efEtEventTime.setText(savedTime.toString())
            saveToEvent = false
            mEvent?.eventTime = savedTime
        } else
            fragmentEventBinding.efEtNotificationTime.setText(savedTime.toString())
        mEvent?.notificationTime = savedTime
    }


}