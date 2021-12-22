package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.olife.data.model.Alarm
import com.example.olife.databinding.FragmentAlarmBinding
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModel
import com.example.olife.utils.TimeUtils
import kotlinx.coroutines.launch
import java.time.LocalTime

class AlarmFragment : Fragment() {
    private lateinit var fragmentAlarmBinding: FragmentAlarmBinding

    private lateinit var alarmsViewModel : AlarmsViewModel

    private var mAlarm: Alarm? = null
    private var timeUtils = TimeUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAlarmBinding = FragmentAlarmBinding.bind(view)
        fragmentAlarmBinding.afTimePicker.setIs24HourView(true)

        alarmsViewModel = (activity as MainActivity).alarmsViewModel

        val args: AlarmFragmentArgs by navArgs()
        mAlarm = args.selectedAlarm

        if (mAlarm == null)
            mAlarm = Alarm(
                null,
                null,
                LocalTime.of(fragmentAlarmBinding.afTimePicker.hour,fragmentAlarmBinding.afTimePicker.minute),
                false,
                false,
                null,
                true
            )
        else{
            fragmentAlarmBinding.afEtTitle.setText(mAlarm?.title)
            fragmentAlarmBinding.afCvAlarmRepeat.isChecked = mAlarm!!.repeat!!
            fragmentAlarmBinding.afCvAlarmVibration.isChecked = mAlarm!!.vibration!!
            fragmentAlarmBinding.afEtRingtone.setText(mAlarm?.soundMemoryLocation)
        }

        fragmentAlarmBinding.afIbCancel.setOnClickListener {
            activity?.onBackPressed()
        }

        fragmentAlarmBinding.afIbConfirm.setOnClickListener {
            mAlarm?.title = fragmentAlarmBinding.afEtTitle.text.toString()
            mAlarm?.repeat = fragmentAlarmBinding.afCvAlarmRepeat.isChecked
            mAlarm?.vibration =fragmentAlarmBinding.afCvAlarmVibration.isChecked
            mAlarm?.soundMemoryLocation = fragmentAlarmBinding.afEtRingtone.text.toString()
            mAlarm?.time = LocalTime.of(fragmentAlarmBinding.afTimePicker.hour,fragmentAlarmBinding.afTimePicker.minute)

            if(mAlarm!!.id == null){
                var alarmId : Long? = null
                viewLifecycleOwner.lifecycleScope.launch{
                    alarmId = alarmsViewModel.saveAlarm(mAlarm!!)
                }.invokeOnCompletion {
                    activity?.onBackPressed()
                }
            }else{
                alarmsViewModel.updateAlarm(mAlarm!!)
                activity?.onBackPressed()
            }
        }
    }


}