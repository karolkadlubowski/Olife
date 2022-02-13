package com.example.olife.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Note
import com.example.olife.databinding.NoteListItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class NotesViewHolder(val binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            if (note.id != null) {
                binding.hfRvNotesLlAddNote.visibility = View.INVISIBLE
                binding.hfRvNotesLlNote.visibility = View.VISIBLE
                binding.hfRvNotesTvTitle.text = note.title
                binding.hfRvNotesTvContent.text = note.content
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(note)
                }
            }

        }

    }

    private var onItemClickListener: ((Note) -> Unit)? = null

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }
}