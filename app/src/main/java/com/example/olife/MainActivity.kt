package com.example.olife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.olife.data.model.Event
import com.example.olife.data.model.VoiceNote
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.adapter.VoiceNotesAdapter
import com.example.olife.presentation.viewmodel.event.EventsViewModel
import com.example.olife.presentation.viewmodel.event.EventsViewModelFactory
import com.example.olife.presentation.viewmodel.note.NotesViewModel
import com.example.olife.presentation.viewmodel.note.NotesViewModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesViewModel
import com.example.olife.utils.CalendarUtils
import com.example.olife.utils.TimeUtils
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
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
    @Inject//provide in di.adapter
    lateinit var voiceNotesAdapter: VoiceNotesAdapter
    lateinit var voiceNotesViewModel: VoiceNotesViewModel

    @Inject
    lateinit var eventsViewModelFactory: EventsViewModelFactory
    @Inject
    lateinit var eventsAdapter: EventsAdapter
    lateinit var eventsViewModel: EventsViewModel

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

        eventsViewModel = ViewModelProvider(this,eventsViewModelFactory)
            .get(EventsViewModel::class.java)
       // voiceNotesViewModel.saveVoiceNote(VoiceNote(null,"elo","elo"))

        //eventsViewModel.saveEvent(Event(null, "Git nazwa",CalendarUtils.getLocalDateFromString("12.10.2021"),TimeUtils.getLocalTimeFromString("09:30"),CalendarUtils.getLocalDateFromString("12.10.2021"),TimeUtils.getLocalTimeFromString("09:30"),"HEHE DOBRE"))
    }
}