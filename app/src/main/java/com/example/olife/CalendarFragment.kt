package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.R
import com.example.olife.databinding.FragmentCalendarBinding
import com.example.olife.presentation.adapter.CalendarAdapter
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class CalendarFragment : Fragment() {
    private lateinit var fragmentCalendarBinding : FragmentCalendarBinding
private lateinit var monthYearText : TextView
private lateinit var calendarRecyclerView: RecyclerView
private lateinit var calendarAdapter: CalendarAdapter
private lateinit var selectedDate:LocalDate


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCalendarBinding = FragmentCalendarBinding.bind(view)

        initCalendarRecyclerView()


        fragmentCalendarBinding.cfBtMonthBefore.setOnClickListener{previousMonthAction(monthYearText)}
        fragmentCalendarBinding.cfBtMonthAfter.setOnClickListener{nextMonthAction(monthYearText)}

    }

    private fun initCalendarRecyclerView() {
        calendarRecyclerView = fragmentCalendarBinding.cfRvCalendar
        monthYearText = fragmentCalendarBinding.cfTvMonthYear
        selectedDate = LocalDate.now()
        setMonthView()
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        calendarAdapter = CalendarAdapter(daysInMonth)

        //var calendarRecyclerViewLayoutManager = GridLayoutManager(activity, 7)
        calendarRecyclerView.layoutManager = GridLayoutManager(activity, 7)

        calendarRecyclerView.adapter=calendarAdapter

        calendarAdapter.setOnItemClickListener {
            if(it!=""){
            var message = "Selected Date " + it + " " + monthYearFromDate(selectedDate)
            Toast.makeText(parentFragment?.context,message,Toast.LENGTH_LONG).show()
             }
        }

    }

    private fun monthYearFromDate(date : LocalDate) : String{
     val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    private fun daysInMonthArray(date : LocalDate) : ArrayList<String>{
        var daysInMonthArray = ArrayList<String>()
        var yearMonth : YearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstOfMonth = selectedDate.withDayOfMonth(1)
        var dayOfWeek = firstOfMonth.dayOfWeek.value

        for(i in 1 until dayOfWeek)
            daysInMonthArray.add("")

        for(i in 1..daysInMonth)
        {
            daysInMonthArray.add(i.toString())
            /*if(i<=dayOfWeek && i > daysInMonth+dayOfWeek){
                daysInMonthArray.add("")
            }
            else
            {
                daysInMonthArray.add((i-dayOfWeek).toString())
            }*/
        }
        return daysInMonthArray
    }

    private fun previousMonthAction(view : View){
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }
    private fun nextMonthAction(view : View){
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

}