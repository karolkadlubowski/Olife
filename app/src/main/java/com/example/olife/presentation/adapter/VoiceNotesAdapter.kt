package com.example.olife.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Note
import com.example.olife.data.model.VoiceNote
import com.example.olife.databinding.VoiceNoteListItemBinding

class VoiceNotesAdapter() : RecyclerView.Adapter<VoiceNotesAdapter.VoiceNotesViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<VoiceNote>(){
        override fun areItemsTheSame(oldItem: VoiceNote, newItem: VoiceNote): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: VoiceNote, newItem: VoiceNote): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoiceNotesViewHolder {
        val binding = VoiceNoteListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return VoiceNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VoiceNotesViewHolder, position: Int) {
        val voiceNote = differ.currentList[position]
        holder.bind(voiceNote)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class VoiceNotesViewHolder(val binding: VoiceNoteListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(voiceNote:VoiceNote){

            binding.hfRvVoiceNoteItemTitle.text=voiceNote.title

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(voiceNote)
                }
            }
        }
    }

    private var onItemClickListener : ((VoiceNote)->Unit)?=null

    fun setOnItemClickListener(listener : (VoiceNote)->Unit){
        onItemClickListener = listener
    }
}