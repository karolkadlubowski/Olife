package com.example.olife

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Note
import com.example.olife.databinding.FragmentCalendarBinding
import com.example.olife.presentation.adapter.CalendarAdapter
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.viewmodel.event.EventsViewModel
import com.example.olife.utils.CalendarUtils
import java.time.LocalDate



class CalendarFragment : Fragment() {
    private lateinit var fragmentCalendarBinding: FragmentCalendarBinding
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter
    //private lateinit var selectedDate: LocalDate

    private val calendarUtils = CalendarUtils

    private lateinit var eventsViewModel : EventsViewModel
    private lateinit var eventsAdapter: EventsAdapter

    init {
        calendarUtils.selectedDate = LocalDate.now()
    }



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

        eventsViewModel = (activity as MainActivity).eventsViewModel
        eventsAdapter = (activity as MainActivity).eventsAdapter

        initEventsRecyclerView()
        setEventListItemTouchHelper()

        initCalendarRecyclerView()


        fragmentCalendarBinding.cfBtMonthBefore.setOnClickListener { previousMonthAction(monthYearText) }
        fragmentCalendarBinding.cfBtMonthAfter.setOnClickListener { nextMonthAction(monthYearText) }



        fragmentCalendarBinding.cfFbAddEvent.setOnClickListener {
            //Bundle().putString(CalendarUtils.getStringFromLocalDate(CalendarUtils.selectedDate))
            findNavController().navigate(
                R.id.action_calendarFragment_to_eventFragment
            )
        }

        eventsAdapter.setOnItemClickListener {
            /*val bundle = Bundle().putSerializable("selected_event",it)
            findNavController().navigate(
                R.id.action_calendarFragment_to_eventFragment,bundle
            )*/
           Bundle().apply {
                    putSerializable("selected_event", it)
                    findNavController().navigate(
                        R.id.action_calendarFragment_to_eventFragment,
                        this
                    )
                }
        }

        //eventsAdapter.notifyDataSetChanged{fragmentCalendarBinding.cfRvEvents.layoutManager?.scrollToPosition(0)}

        //fragmentCalendarBinding.cfRvEvents.layoutManager?.smoothScrollToPosition(fragmentCalendarBinding.cfRvEvents,fragmentCalendarBinding.cfRvEvents.)
/*
        fragmentCalendarBinding.cfRvEvents.layoutManager?.on{
            fragmentCalendarBinding.cfRvEvents.layoutManager?.scrollToPosition(0)
        }

*/

        Handler(Looper.getMainLooper()).postDelayed({
            fragmentCalendarBinding.cfRvEvents.layoutManager?.scrollToPosition(0)
        }, 400)




    }


    private fun initEventsRecyclerView() {
        fragmentCalendarBinding.cfRvEvents.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }
    }

    private fun initCalendarRecyclerView() {
        calendarRecyclerView = fragmentCalendarBinding.cfRvCalendar
        monthYearText = fragmentCalendarBinding.cfTvMonthYear
        setMonthView()
    }


    private fun setMonthView() {
        monthYearText.text = calendarUtils.monthYearFromDate(calendarUtils.selectedDate)
        val daysInMonth = calendarUtils.getDaysInMonthArray(calendarUtils.selectedDate)
        calendarAdapter = CalendarAdapter(daysInMonth)

        //var calendarRecyclerViewLayoutManager = GridLayoutManager(activity, 7)
        calendarRecyclerView.layoutManager = GridLayoutManager(activity, 7)

        calendarRecyclerView.adapter = calendarAdapter

        eventsViewModel.getEventsOnCertainDay(CalendarUtils.selectedDate).observe(viewLifecycleOwner,{
            eventsAdapter.differ.submitList(it)
        })

        calendarAdapter.setOnItemClickListener { localDate ->
            if (localDate != null) {
               var message = "Selected Date $localDate"
                //Toast.makeText(parentFragment?.context, message, Toast.LENGTH_LONG).show()
                eventsViewModel.getEventsOnCertainDay(localDate).observe(viewLifecycleOwner,{
                    eventsAdapter.differ.submitList(it)
                })
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

    private fun setEventListItemTouchHelper(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //up and down so now
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val event = eventsAdapter.differ.currentList[viewHolder.adapterPosition]
                eventsViewModel.deleteEvent(event)
            }


        }).attachToRecyclerView(fragmentCalendarBinding.cfRvEvents)
    }

}

//private fun RecyclerView.LayoutManager?.onItemsChanged(function: () -> Unit?) {

//}
