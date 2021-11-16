package com.example.olife.presentation.adapter

import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.databinding.CalendarCellListItemBinding
import com.example.olife.utils.CalendarUtils
import java.time.LocalDate

class CalendarAdapter(private val days: ArrayList<LocalDate?>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CalendarCellListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        val w = DisplayMetrics().widthPixels/7
        binding.hfTvCalendarCell.width= parent.width/7
        val h=DisplayMetrics().heightPixels/6
        binding.hfTvCalendarCell.height=parent.height/7
        binding.hfTvCalendarCell.gravity=Gravity.CENTER_HORIZONTAL
        val typedValue = TypedValue()
        parent.context.theme.resolveAttribute(android.R.attr.selectableItemBackground,typedValue,
            true
        )
        binding.hfTvCalendarCell.setBackgroundResource(typedValue.resourceId)
        //binding.hfItCalendarCell.layoutParams.
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(days[position])
        val date = days[position]
        /*if(date!="" &&){
            //holder.binding.hfTvCalendarCell.
        }*/
    }

    override fun getItemCount(): Int {
        return days.size
    }

    inner class CalendarViewHolder(val binding : CalendarCellListItemBinding) : RecyclerView.ViewHolder(binding.root){
       //val dayOfMonth : String? = null
       // val itemLayout = binding.cfLl
       private var date: LocalDate? = null

       fun bind(dayOfMonth: LocalDate?){
           date = dayOfMonth
           if (dayOfMonth != null) {
               binding.hfTvCalendarCell.text=dayOfMonth.dayOfMonth.toString()
           }else
               binding.hfTvCalendarCell.text=""
           binding.root.setOnClickListener {
               onItemClickListener?.let {
                   it(dayOfMonth)
               }
           }
        }


    }
    private var onItemClickListener : ((LocalDate?)->Unit)?=null

    fun setOnItemClickListener(listener:(LocalDate?)->Unit){
        onItemClickListener=listener
    }
}