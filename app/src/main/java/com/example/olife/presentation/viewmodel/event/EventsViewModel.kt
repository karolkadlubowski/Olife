package com.example.olife.presentation.viewmodel.event

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.olife.data.model.Event
import com.example.olife.domain.usecase.event.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventsViewModel(
    private val app: Application,
    private val saveEventUseCase: SaveEventUseCase,
    private val getSavedEventsUseCase: GetSavedEventsUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val getEventsOnCertainDayUseCase: GetEventsOnCertainDayUseCase
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

    fun getEventsOnCertainDay(localDate: LocalDate) = liveData {
        getEventsOnCertainDayUseCase.execute(localDate).collect {
            emit(it)
        }
    }
}