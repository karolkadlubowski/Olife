package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.olife.data.model.Note
import com.example.olife.databinding.FragmentNoteInfoBinding
import com.example.olife.presentation.viewmodel.note.NotesViewModel


class NoteInfoFragment : Fragment() {
    private lateinit var fragmentNoteInfoBinding : FragmentNoteInfoBinding
    private lateinit var notesViewModel : NotesViewModel
    private lateinit var mNote : Note


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNoteInfoBinding = FragmentNoteInfoBinding.bind(view)
        val args : NoteInfoFragmentArgs by navArgs()
        mNote = args.selectedNote

        fragmentNoteInfoBinding.nfTvTitle.setText(mNote.title)
        fragmentNoteInfoBinding.nfTvContent.setText(mNote.content)

        notesViewModel = (activity as MainActivity).notesViewModel
    }

    override fun onDestroyView() {
        if(mNote.id==null){
            notesViewModel.saveNote(Note(null,fragmentNoteInfoBinding.nfTvTitle.text.toString(),fragmentNoteInfoBinding.nfTvContent.text.toString()))
        }
        else
        {
            notesViewModel.updateNote(Note(mNote.id,fragmentNoteInfoBinding.nfTvTitle.text.toString(),fragmentNoteInfoBinding.nfTvContent.text.toString()))
        }
        super.onDestroyView()
    }

}