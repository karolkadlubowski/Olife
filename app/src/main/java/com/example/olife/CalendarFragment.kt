package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.databinding.FragmentCalendarBinding
import com.example.olife.presentation.adapter.CalendarAdapter
import com.example.olife.utils.CalendarUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class CalendarFragment : Fragment() {
    private lateinit var fragmentCalendarBinding: FragmentCalendarBinding
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    //private lateinit var selectedDate: LocalDate

    private val calendarUtils = CalendarUtils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //calendarRecyclerView.height=fragmentCalendarBinding.
        fragmentCalendarBinding = FragmentCalendarBinding.bind(view)

        initCalendarRecyclerView()


        fragmentCalendarBinding.cfBtMonthBefore.setOnClickListener {
            previousMonthAction(
                monthYearText
            )
        }
        fragmentCalendarBinding.cfBtMonthAfter.setOnClickListener { nextMonthAction(monthYearText) }

        fragmentCalendarBinding.cfFbAddEvent.setOnClickListener {
            findNavController().navigate(
                R.id.action_calendarFragment_to_eventFragment
            )
        }

    }

    private fun initCalendarRecyclerView() {
        calendarRecyclerView = fragmentCalendarBinding.cfRvCalendar
        monthYearText = fragmentCalendarBinding.cfTvMonthYear
        calendarUtils.selectedDate = LocalDate.now()
        setMonthView()
    }

    private fun setMonthView() {
        monthYearText.text = calendarUtils.monthYearFromDate(calendarUtils.selectedDate)
        val daysInMonth = calendarUtils.getDaysInMonthArray(calendarUtils.selectedDate)
        calendarAdapter = CalendarAdapter(daysInMonth)

        //var calendarRecyclerViewLayoutManager = GridLayoutManager(activity, 7)
        calendarRecyclerView.layoutManager = GridLayoutManager(activity, 7)

        calendarRecyclerView.adapter = calendarAdapter

        calendarAdapter.setOnItemClickListener {
            if (it != null) {
               var message = "Selected Date $it"
                Toast.makeText(parentFragment?.context, message, Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun previousMonthAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.minusMonths(1)
        setMonthView()
    }

    private fun nextMonthAction(view: View) {
        calendarUtils.selectedDate = calendarUtils.selectedDate.plusMonths(1)
        setMonthView()
    }

}