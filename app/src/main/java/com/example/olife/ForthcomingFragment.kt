package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.databinding.FragmentForthcomingBinding
import com.example.olife.presentation.adapter.AlarmsAdapter
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModel
import com.example.olife.presentation.viewmodel.event.EventsViewModel
import com.example.olife.utils.AlarmUtils
import java.time.LocalDate

class ForthcomingFragment : Fragment() {

    private lateinit var fragmentForthcomingBinding: FragmentForthcomingBinding

    private lateinit var forthcomingEventsViewModel : EventsViewModel

    private lateinit var forthcomingEventsAdapter : EventsAdapter

    private lateinit var alarmsAdapter: AlarmsAdapter

    private lateinit var alarmsViewModel : AlarmsViewModel

    private var alarmUtils = AlarmUtils

 /*   private lateinit var todayEventsViewModel : EventsViewModel
    private lateinit var tomorrowEventsViewModel: EventsViewModel
    private lateinit var dayAfterTomorrowEventsViewModel: EventsViewModel

    private lateinit var todayEventsAdapter : EventsAdapter
    private lateinit var tomorrowEventsAdapter: EventsAdapter
    private lateinit var dayAfterTomorrowEventsAdapter : EventsAdapter*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forthcoming, container, false)
    }

    override fun onResume() {
        super.onResume()
        /*if(todayEventsAdapter.differ.currentList.isEmpty())
            fragmentForthcomingBinding.ffLlToday.visibility=View.GONE
        else
            fragmentForthcomingBinding.ffLlToday.visibility=View.VISIBLE

        if(tomorrowEventsAdapter.differ.currentList.isEmpty())
            fragmentForthcomingBinding.ffLlTomorrow.visibility=View.GONE
        else
            fragmentForthcomingBinding.ffLlTomorrow.visibility=View.VISIBLE

        fragmentForthcomingBinding.ffLlDayAfterTomorrow.visibility = if(dayAfterTomorrowEventsAdapter.differ.currentList.isEmpty()) View.GONE else View.VISIBLE*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragmentForthcomingBinding = FragmentForthcomingBinding.bind(view)
        forthcomingEventsViewModel = (activity as MainActivity).forthcomingEventsViewModel
        forthcomingEventsAdapter = (activity as MainActivity).forthcomingEventsAdapter

        alarmsAdapter = (activity as MainActivity).alarmsAdapter
        alarmsViewModel = (activity as MainActivity).alarmsViewModel


        initForthcomingRecyclerView()
        initAlarmsRecyclerView()

        setAlarmListItemTouchHelper()

       /* fragmentForthcomingBinding.ffRvAlarms.setOnClickListener {

        }*/

        alarmsAdapter.setOnItemClickListener {
            //alarmsAdapter.
            Bundle().apply {
                putSerializable("selected_alarm",it)
                findNavController().navigate(R.id.action_forthcomingFragment_to_alarmFragment,this)
            }

        }

        fragmentForthcomingBinding.ffIbAddAlarm.setOnClickListener {
            findNavController().navigate(R.id.action_forthcomingFragment_to_alarmFragment)
        }



/*
        todayEventsViewModel = (activity as MainActivity).todayEventsViewModel
        tomorrowEventsViewModel = (activity as MainActivity).tomorrowEventsViewModel
        dayAfterTomorrowEventsViewModel = (activity as MainActivity).dayAfterTomorrowEventsViewModel

        todayEventsAdapter = (activity as MainActivity).todayEventsAdapter
        tomorrowEventsAdapter = (activity as MainActivity).tomorrowEventsAdapter
        dayAfterTomorrowEventsAdapter = (activity as MainActivity).dayAfterTomorrowEventsAdapter


        initTodayRecyclerView()
        initTomorrowRecyclerView()
        initDayAfterTomorrowRecyclerView()
*/
        //setTodayEventListItemTouchHelper()

      /*  todayEventsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
                /*if(todayEventsAdapter.differ.currentList.isEmpty())
                    fragmentForthcomingBinding.ffLlToday.visibility=View.GONE
                else
                    fragmentForthcomingBinding.ffLlToday.visibility=View.VISIBLE*/
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                fragmentForthcomingBinding.ffLlToday.visibility = (if (todayEventsAdapter.itemCount == 0) View.VISIBLE else View.GONE)
            }
        })
        super.onViewCreated(view, savedInstanceState)

       */
    }

    private fun initAlarmsRecyclerView() {
        fragmentForthcomingBinding.ffRvAlarms.apply{
            adapter=alarmsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }
        alarmsViewModel.getSavedAlarms().observe(viewLifecycleOwner,{
            alarmsAdapter.differ.submitList(it)
        })
    }

    private fun initForthcomingRecyclerView() {
        fragmentForthcomingBinding.ffRvForthcomingEvents.apply {
            adapter = forthcomingEventsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }

        forthcomingEventsViewModel.getEventsAtCertainWeek(LocalDate.now()).observe(viewLifecycleOwner,{
            forthcomingEventsAdapter.differ.submitList(it)
        })
    }

    private fun setAlarmListItemTouchHelper(){
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
                /*val alarm = eventsAdapter.differ.currentList[viewHolder.adapterPosition]
                eventsViewModel.deleteEvent(event)
                notificationUtils.createEventNotificationChannel(context!!)
                notificationUtils.deleteNotification(context!!,event)*/

                val alarm = alarmsAdapter.differ.currentList[viewHolder.adapterPosition]
                alarmsViewModel.deleteAlarm(alarm)
                alarmUtils.createAlarmNotificationChannel(context!!)
                alarmUtils.cancelAlarm(context!!,alarm)
            }


        }).attachToRecyclerView(fragmentForthcomingBinding.ffRvAlarms)
    }


/*
    private fun initTodayRecyclerView(){
        fragmentForthcomingBinding.ffRvTodayEvents.apply {
            adapter = todayEventsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }

        todayEventsViewModel.getEventsOnCertainDay(LocalDate.now()).observe(viewLifecycleOwner,{
            todayEventsAdapter.differ.submitList(it)
        })
    }

    private fun initTomorrowRecyclerView() {
        fragmentForthcomingBinding.ffRvTomorrowEvents.apply {
            adapter = tomorrowEventsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }

        tomorrowEventsViewModel.getEventsOnCertainDay(LocalDate.now().plusDays(1)).observe(viewLifecycleOwner,{
            tomorrowEventsAdapter.differ.submitList(it)



        })
    }

    private fun initDayAfterTomorrowRecyclerView() {
        fragmentForthcomingBinding.ffRvDayAfterTomorrowEvents.apply {
            adapter = dayAfterTomorrowEventsAdapter
            layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        }

        dayAfterTomorrowEventsViewModel.getEventsOnCertainDay(LocalDate.now().plusDays(2)).observe(viewLifecycleOwner,{
            dayAfterTomorrowEventsAdapter.differ.submitList(it)



        })
    }*/
/*
    private fun setTodayEventListItemTouchHelper(){
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
                val event = todayEventsAdapter.differ.currentList[viewHolder.adapterPosition]
                todayEventsViewModel.deleteEvent(event)
            }


        }).attachToRecyclerView(fragmentForthcomingBinding.ffRvTodayEvents)
    }

 */
}