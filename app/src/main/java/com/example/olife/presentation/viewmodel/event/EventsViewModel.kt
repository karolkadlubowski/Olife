package com.example.olife.presentation.viewmodel.event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.olife.data.model.Event
import com.example.olife.domain.usecase.event.DeleteEventUseCase
import com.example.olife.domain.usecase.event.GetSavedEventsUseCase
import com.example.olife.domain.usecase.event.SaveEventUseCase
import com.example.olife.domain.usecase.event.UpdateEventUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModel(
    private val app: Application,
    private val saveEventUseCase: SaveEventUseCase,
    private val getSavedEventsUseCase: GetSavedEventsUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase
) : AndroidViewModel(app) {

    fun saveEvent(event: Event) = viewModelScope.launch {
        saveEventUseCase.execute(event)
    }

    fun getSavedEvents() = liveData {
        getSavedEventsUseCase.execute().collect {
            emit(it)
        }
    }

    fun updateEvent(event: Event) = viewModelScope.launch {
        updateEventUseCase.execute(event)
    }

    fun deleteEvent(event: Event) = viewModelScope.launch {
        deleteEventUseCase.execute(event)
    }

}