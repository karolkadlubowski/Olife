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

    private lateinit var forthcomingEventsViewModel: EventsViewModel

    private lateinit var forthcomingEventsAdapter: EventsAdapter

    private lateinit var alarmsAdapter: AlarmsAdapter

    private lateinit var alarmsViewModel: AlarmsViewModel

    private var alarmUtils = AlarmUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forthcoming, container, false)
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

        alarmsAdapter.setOnItemClickListener {
            Bundle().apply {
                putSerializable("selected_alarm", it)
                findNavController().navigate(R.id.action_forthcomingFragment_to_alarmFragment, this)
            }
        }

        fragmentForthcomingBinding.ffIbAddAlarm.setOnClickListener {
            findNavController().navigate(R.id.action_forthcomingFragment_to_alarmFragment)
        }
    }

    private fun initAlarmsRecyclerView() {
        fragmentForthcomingBinding.ffRvAlarms.apply {
            adapter = alarmsAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
        alarmsViewModel.getSavedAlarms().observe(viewLifecycleOwner, {
            alarmsAdapter.differ.submitList(it)
        })
    }

    private fun initForthcomingRecyclerView() {
        fragmentForthcomingBinding.ffRvForthcomingEvents.apply {
            adapter = forthcomingEventsAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }

        forthcomingEventsViewModel.getEventsAtCertainWeek(LocalDate.now())
            .observe(viewLifecycleOwner, {
                forthcomingEventsAdapter.differ.submitList(it)
            })
    }

    private fun setAlarmListItemTouchHelper() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val alarm = alarmsAdapter.differ.currentList[viewHolder.adapterPosition]
                alarmsViewModel.deleteAlarm(alarm)
                alarmUtils.createAlarmNotificationChannel(context!!)
                alarmUtils.cancelAlarm(context!!, alarm)
            }


        }).attachToRecyclerView(fragmentForthcomingBinding.ffRvAlarms)
    }
}