package com.example.olife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.olife.data.model.VoiceNote
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.adapter.VoiceNotesAdapter
import com.example.olife.presentation.viewmodel.note.NotesViewModel
import com.example.olife.presentation.viewmodel.note.NotesViewModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notesViewModelFactory : NotesViewModelFactory
    @Inject
    lateinit var notesAdapter: NotesAdapter
    lateinit var notesViewModel: NotesViewModel


    @Inject
    lateinit var voiceNotesViewModelFactory: VoiceNotesModelFactory
    @Inject
    lateinit var voiceNotesAdapter: VoiceNotesAdapter
    lateinit var voiceNotesViewModel: VoiceNotesViewModel

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

        voiceNotesViewModel = ViewModelProvider(this,voiceNotesViewModelFactory)
            .get(VoiceNotesViewModel::class.java)
       // voiceNotesViewModel.saveVoiceNote(VoiceNote(null,"elo","elo"))
    }
}