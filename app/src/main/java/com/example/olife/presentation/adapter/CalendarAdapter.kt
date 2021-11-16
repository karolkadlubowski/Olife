package com.example.olife.presentation.adapter

import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Note
import com.example.olife.databinding.CalendarCellListItemBinding
class CalendarAdapter(private val daysOfMonth: ArrayList<String>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CalendarCellListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        val w = DisplayMetrics().widthPixels/7
        binding.hfItCalendarCell.width= parent.width/7
        val h=DisplayMetrics().heightPixels/6
        binding.hfItCalendarCell.height=parent.height/7
        binding.hfItCalendarCell.gravity=Gravity.CENTER_HORIZONTAL
        //binding.hfItCalendarCell.layoutParams.
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(daysOfMonth[position])
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    inner class CalendarViewHolder(val binding : CalendarCellListItemBinding) : RecyclerView.ViewHolder(binding.root){
       //val dayOfMonth : String? = null

       fun bind(dayOfMonth: String){
            binding.hfItCalendarCell.text=dayOfMonth

           binding.root.setOnClickListener {
               onItemClickListener?.let {
                   it(dayOfMonth)
               }
           }
        }


    }
    private var onItemClickListener : ((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){
        onItemClickListener=listener
    }
}