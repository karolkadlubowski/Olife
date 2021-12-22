package com.example.olife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import com.example.olife.data.model.VoiceNote
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.presentation.adapter.AlarmsAdapter
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.adapter.VoiceNotesAdapter
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModel
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModelFactory
import com.example.olife.presentation.viewmodel.event.EventsViewModel
import com.example.olife.presentation.viewmodel.event.EventsViewModelFactory
import com.example.olife.presentation.viewmodel.note.NotesViewModel
import com.example.olife.presentation.viewmodel.note.NotesViewModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesViewModel
import com.example.olife.utils.CalendarUtils
import com.example.olife.utils.TimeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notesViewModelFactory: NotesViewModelFactory

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

    /*
        lateinit var todayEventsViewModel: EventsViewModel
        lateinit var tomorrowEventsViewModel: EventsViewModel
        lateinit var dayAfterTomorrowEventsViewModel: EventsViewModel*/
/*
    @Inject
    lateinit var todayEventsAdapter: EventsAdapter
    @Inject
    lateinit var tomorrowEventsAdapter: EventsAdapter
    @Inject
    lateinit var dayAfterTomorrowEventsAdapter: EventsAdapter
*/
    @Inject
    lateinit var forthcomingEventsAdapter: EventsAdapter

    lateinit var forthcomingEventsViewModel: EventsViewModel

    @Inject
    lateinit var alarmsViewModelFactory: AlarmsViewModelFactory

    @Inject
    lateinit var alarmsAdapter: AlarmsAdapter

    lateinit var alarmsViewModel: AlarmsViewModel


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
        notesViewModel = ViewModelProvider(this, notesViewModelFactory)
            .get(NotesViewModel::class.java)

        voiceNotesViewModel = ViewModelProvider(this, voiceNotesViewModelFactory)
            .get(VoiceNotesViewModel::class.java)

        eventsViewModel = ViewModelProvider(this, eventsViewModelFactory)
            .get(EventsViewModel::class.java)

        forthcomingEventsViewModel = ViewModelProvider(this, eventsViewModelFactory)
            .get(EventsViewModel::class.java)

        alarmsViewModel = ViewModelProvider(this, alarmsViewModelFactory).get(AlarmsViewModel::class.java)
        alarmsAdapter.alarmsViewModel=alarmsViewModel

        //alarmsAdapter= AlarmsAdapter(alarmsViewModel)

       /* GlobalScope.launch {
            alarmsViewModel.saveAlarm(Alarm(null,"Mordo", LocalTime.now(),true,false,"",true))
            alarmsViewModel.saveAlarm(Alarm(null,"Zaluj", LocalTime.now(),true,true,"",false))
        }*/

/*
        todayEventsViewModel = ViewModelProvider(this, eventsViewModelFactory)
            .get(EventsViewModel::class.java)
        tomorrowEventsViewModel = ViewModelProvider(this, eventsViewModelFactory)
            .get(EventsViewModel::class.java)
        dayAfterTomorrowEventsViewModel = ViewModelProvider(this, eventsViewModelFactory)
            .get(EventsViewModel::class.java)
*/
        // voiceNotesViewModel.saveVoiceNote(VoiceNote(null,"elo","elo"))

        //eventsViewModel.saveEvent(Event(null, "Evento",CalendarUtils.getLocalDateFromString("24.11.2021"),TimeUtils.getLocalTimeFromString("09:30"),CalendarUtils.getLocalDateFromString("25.11.2021"),TimeUtils.getLocalTimeFromString("09:30"),"Pomaranczowe"))
    }
}