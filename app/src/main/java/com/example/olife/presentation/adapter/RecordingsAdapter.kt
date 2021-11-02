package com.example.olife.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Recording
import com.example.olife.databinding.RecordingListItemBinding

class RecordingsAdapter( private var list: ArrayList<Recording>) : RecyclerView.Adapter<RecordingsAdapter.RecordingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingsViewHolder {
        val binding = RecordingListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecordingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordingsViewHolder, position: Int) {
        val recording = list[position]

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class RecordingsViewHolder(val binding: RecordingListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(recording:Recording){
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(recording)
                }
            }
        }

        private var onItemClickListener : ((Recording)->Unit)?=null
    }
}