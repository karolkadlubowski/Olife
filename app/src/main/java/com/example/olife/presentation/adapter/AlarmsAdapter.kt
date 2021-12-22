package com.example.olife.presentation.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.MainActivity
import com.example.olife.data.model.Alarm
import com.example.olife.databinding.AlarmListItemBinding
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModel
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModelFactory
import com.example.olife.utils.TimeUtils
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlarmsAdapter() : RecyclerView.Adapter<AlarmsAdapter.AlarmsViewHolder>() {

    lateinit var alarmsViewModel : AlarmsViewModel

    private val callback = object : DiffUtil.ItemCallback<Alarm>(){
        override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsViewHolder {
       val binding = AlarmListItemBinding
           .inflate(LayoutInflater.from(parent.context),parent,false)

        return AlarmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmsViewHolder, position: Int) {
        val alarm = differ.currentList[position]
        holder.bind(alarm)

        //holder.binding.liSwAlarmSwitch.setOnCheckedChangeListener() DOESNT WORK IN THIS UTILITY

        holder.binding.liSwAlarmSwitch.setOnClickListener {
            alarm.isTurnedOn=holder.binding.liSwAlarmSwitch.isChecked
            alarmsViewModel.updateAlarm(alarm)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    private val timeUtils = TimeUtils

    private var onItemClickListener : ((Alarm)->Unit)?=null

    fun setOnItemClickListener(listener : (Alarm)->Unit){
        onItemClickListener = listener
    }

    inner class AlarmsViewHolder(val binding: AlarmListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(alarm: Alarm){
            binding.liSwAlarmSwitch.isChecked=alarm.isTurnedOn!!
            binding.liTvAlarmRepeat.text = if(alarm.repeat!!) "Everyday" else " Only once"
            binding.liTvAlarmTime.text = timeUtils.getStringFromLocalTime(alarm.time!!)
            binding.liTvAlarmTitle.text = alarm.title!!

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(alarm)
                }
            }
        }
    }
}
