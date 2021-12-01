package com.example.olife.presentation.adapter

import android.graphics.Color
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.R
import com.example.olife.databinding.CalendarCellListItemBinding
import com.example.olife.utils.CalendarUtils
import java.time.LocalDate

class CalendarAdapter(private val days: ArrayList<LocalDate?>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    val calendarUtils = CalendarUtils

    var selectedItem : Int? = null

    var bindingsList : ArrayList<CalendarCellListItemBinding>? =
        ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CalendarCellListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        bindingsList?.add(binding)
        binding.cfTvCalendarCell.width= parent.width/7
        //binding.hfTvCalendarCell.height=parent.height/5
        binding.cfTvCalendarCell.gravity=Gravity.CENTER_HORIZONTAL
        val typedValue = TypedValue()
        parent.context.theme.resolveAttribute(android.R.attr.selectableItemBackground,typedValue,
            true
        )

        binding.cfLl.setBackgroundResource(typedValue.resourceId)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(days[position],position)

        val date = days[position]
        if(date!=null && date == calendarUtils.selectedDate){
            holder.binding.cfLl.setBackgroundColor(Color.LTGRAY)
            selectedItem=position
        }

    }


    override fun getItemCount(): Int {
        return days.size
    }

    inner class CalendarViewHolder(val binding : CalendarCellListItemBinding) : RecyclerView.ViewHolder(binding.root){
       private var date: LocalDate? = null

       fun bind(dayOfMonth: LocalDate?,position: Int){

           date = dayOfMonth
           if (dayOfMonth != null) {
               binding.cfTvCalendarCell.text=dayOfMonth.dayOfMonth.toString()
           }else
               binding.cfTvCalendarCell.text=""
           binding.root.setOnClickListener {
               if(date!=null){
                   calendarUtils.selectedDate = date as LocalDate
                   selectedItem?.let { it1 -> bindingsList?.get(it1)?.cfLl!!.setBackgroundColor(Color.TRANSPARENT) }
                   selectedItem = position
                    binding.cfLl.setBackgroundColor(Color.LTGRAY)

               }

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