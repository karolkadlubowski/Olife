package com.example.olife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.olife.data.model.Note
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.viewmodel.NotesViewModel
import com.example.olife.presentation.viewmodel.NotesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notesViewModelFactory : NotesViewModelFactory
    @Inject
    lateinit var notesAdapter: NotesAdapter
    lateinit var notesViewModel: NotesViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvOlife.setupWithNavController(
            navController
        )
        notesViewModel = ViewModelProvider(this,notesViewModelFactory)
            .get(NotesViewModel::class.java)
        //notesViewModel.saveNote(Note(null,"Tytul 1","Opis"))
        //notesViewModel.saveNote(Note(null,"Tytul 2","Opis2"))
        //notesViewModel.saveNote(Note(null,"Tytul 3","Opis3"))

       //val elo= notesViewModel.getSavedNotes()
       // val eloval = elo.value
    }
}