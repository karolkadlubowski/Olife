package com.example.olife.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Event
import com.example.olife.databinding.EventListItemBinding
import com.example.olife.utils.CalendarUtils
import com.example.olife.utils.TimeUtils

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = EventListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class EventsViewHolder(val binding: EventListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {

            binding.cfLiTvEventTitle.text = event.name
            binding.cfLiTvEventHour.text = event.eventTime?.let { it ->
                TimeUtils.getStringFromLocalTime(
                    it
                )
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(event)
                }
            }
        }
    }

    private var onItemClickListener: ((Event) -> Unit)? = null

    fun setOnItemClickListener(listener: (Event) -> Unit) {
        onItemClickListener = listener
    }


}