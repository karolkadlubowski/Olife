package com.example.olife

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.olife.databinding.FragmentEventBinding
import com.example.olife.utils.CalendarUtils
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class EventFragment : Fragment(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    private lateinit var fragmentEventBinding: FragmentEventBinding
    private  val calendarUtils = CalendarUtils
    /*lateinit var date : LocalDate
    lateinit var time : LocalTime*/

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    lateinit var savedDate : LocalDate
    lateinit var savedTime : LocalTime

    
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
        pickDate()
        pickHour()
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun getTime(){
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(){

        fragmentEventBinding.efEtEventDate.setOnClickListener {
            getDateCalendar()
            parentFragment?.context?.let { DatePickerDialog(it,this,year,month,day).show() }

        }

        fragmentEventBinding.efEtNotificationDate.setOnClickListener {
            getDateCalendar()
            parentFragment?.context?.let { DatePickerDialog(it,this,year,month,day).show() }

        }

    }

    private fun pickHour(){
        fragmentEventBinding.efEtEventTime.setOnClickListener {
            getTime()
            TimePickerDialog(parentFragment?.context,this,hour,minute,true).show()
        }

        fragmentEventBinding.efEtNotificationTime.setOnClickListener {
            getTime()
            TimePickerDialog(parentFragment?.context,this,hour,minute,true).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDate=LocalDate.of(year,month,dayOfMonth)
        fragmentEventBinding.efEtEventDate.setText(calendarUtils.getFormattedDate(savedDate))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedTime=LocalTime.of(hourOfDay,minute)
        fragmentEventBinding.efEtEventTime.setText(savedTime.toString())
    }

}