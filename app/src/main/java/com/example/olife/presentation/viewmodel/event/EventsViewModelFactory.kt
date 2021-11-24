package com.example.olife.presentation.viewmodel.event

import android.app.Application
import androidx.lifecycle.*
import com.example.olife.data.model.Event
import com.example.olife.domain.usecase.event.*
import com.example.olife.presentation.viewmodel.note.NotesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModelFactory(
    private val app: Application,
    private val saveEventUseCase: SaveEventUseCase,
    private val getSavedEventsUseCase: GetSavedEventsUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val getEventsOnCertainDayUseCase: GetEventsOnCertainDayUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventsViewModel(
            app,
            saveEventUseCase,
            getSavedEventsUseCase,
            updateEventUseCase,
            deleteEventUseCase,
            getEventsOnCertainDayUseCase
        ) as T
    }

}
