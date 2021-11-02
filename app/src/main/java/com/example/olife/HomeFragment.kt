package com.example.olife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.olife.data.model.Note
import com.example.olife.data.model.Recording
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.databinding.FragmentHomeBinding
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.adapter.RecordingsAdapter
import com.example.olife.presentation.viewmodel.NotesViewModel

import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    //private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    // private val binding get() = _binding!!

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var notesAdapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = FragmentHomeBinding.inflate(inflater,container,false)
        // val view = binding.root

        //return view

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        notesViewModel = (activity as MainActivity).notesViewModel
        notesAdapter = (activity as MainActivity).notesAdapter

        //val swipeGesture = object :

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //up and down so now
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val note = notesAdapter.differ.currentList[viewHolder.adapterPosition]
                    notesViewModel.deleteNote(note)
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if(viewHolder.adapterPosition==notesAdapter.differ.currentList.size-1) return 0
                return super.getSwipeDirs(recyclerView, viewHolder)
            }

        }).attachToRecyclerView(fragmentHomeBinding.hfRvNotes)

        notesAdapter.setOnItemClickListener {
            //Toast.makeText(parentFragment?.context,it.id.toString(),Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                if (it.id == null)
                    putSerializable("selected_note", Note(null, null, null))
                else
                    putSerializable("selected_note", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_noteInfoFragment,
                bundle
            )
//            if(it.id==null){
//                notesViewModel.saveNote(Note(null,null,null))
//            }
        }
/*
        setFragmentResultListener("saveNoteRequestKey"){requestKey, bundle ->
            val result = bundle.getSerializable("saveNoteBundleKey") as Note
            //notesViewModel.saveNote(No)
        }
*/
        initNotesRecyclerView()
        notesViewModel.getSavedNotes().observe(viewLifecycleOwner, {
            var editedList: ArrayList<Note> = ArrayList(it)
            editedList.add(Note(null, null, null))
            notesAdapter.differ.submitList(editedList)
        })
        //viewNoteList()

        fragmentHomeBinding.hfTvWelcome.text = getTimeFromWelcoming()

        var rec1 = Recording("l", "o")
        var rec2 = Recording("l2", "o2")
        var rec3 = Recording("l3", "o3")
        var rec4 = Recording("l4", "o4")
        var rec5 = Recording("l5", "o5")

        var records = ArrayList<Recording>()
        records.add(rec1)
        records.add(rec2)
        records.add(rec3)
        records.add(rec4)
        records.add(rec5)

        val recordingsAdapter1 = RecordingsAdapter(records)

        fragmentHomeBinding.hfRvRecordings.adapter = recordingsAdapter1
        fragmentHomeBinding.hfRvRecordings.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
/*
        var notes = ArrayList<Note>()
        notes.add(Note(1, "1", "1"))
        notes.add(Note(2, "2", "2"))
        notes.add(Note(3, "3", "3"))
        notes.add(Note(4, "4", "4"))
        notes.add(Note(5, "5", "5"))

        val notesAdapter1 = NotesAdapter(notes)*/
        var gridLayoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
        //fragmentHomeBinding.hfRvNotes.layoutManager = gridLayoutManager
        //fragmentHomeBinding.hfRvNotes.setHasFixedSize(true)
        //fragmentHomeBinding.hfRvNotes.adapter = notesAdapter1
    }

    private fun initNotesRecyclerView() {
        fragmentHomeBinding.hfRvNotes.apply {
            adapter = notesAdapter
            //layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)

        }
    }

    /*
        override fun onDestroyView() {
            super.onDestroyView()
            _binding=null
        }
    */
    private fun getTimeFromWelcoming(): String {
        val dt = Date()
        val hours: Int = dt.getHours()
        if (hours in 1..11) {
            return "Good morning"
        } else if (hours in 12..17) {
            return "Good afternoon"
        } else if (hours in 18..24) {
            return "Good evening"
        }
        return "Hello"
    }
}