package com.example.olife.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.olife.data.model.Note
import com.example.olife.databinding.NoteListItemBinding
/*
class NotesAdapter(private var list: ArrayList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NotesViewHolder(val binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {//nazwa binding chwyta sama po utworzeniu xmla

    }
}*/

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    private val callback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)
/*
    val differWithAddedPlus = editDiffer()

    fun editDiffer() : AsyncListDiffer<Note>{
        val editedDiffer = differ
        editedDiffer.currentList.add(Note(null,null,null))
        return editedDiffer
    }
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class NotesViewHolder(val binding : NoteListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note:Note){
            if(note.id!=null){
                binding.hfRvNotesLlAddNote.visibility=View.INVISIBLE
                binding.hfRvNotesLlNote.visibility=View.VISIBLE
                binding.hfRvNotesTvTitle.text=note.title
                binding.hfRvNotesTvContent.text=note.content
            }

            binding.root.setOnClickListener {
                //binding.hfRvNotesLlAddNote.visibility= View.INVISIBLE
                //binding.hfRvNotesLlNote.visibility= View.VISIBLE
                onItemClickListener?.let {
                    it(note)
                }
            }

        }

    }
    private var onItemClickListener : ((Note)->Unit)?=null

    fun setOnItemClickListener(listener : (Note)->Unit){
        onItemClickListener = listener
    }
}