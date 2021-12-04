package com.example.olife.presentation.viewmodel.event

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.olife.data.model.Event
import com.example.olife.domain.usecase.event.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.math.absoluteValue

class EventsViewModel(
    private val app: Application,
    private val saveEventUseCase: SaveEventUseCase,
    private val getSavedEventsUseCase: GetSavedEventsUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val getEventsOnCertainDayUseCase: GetEventsOnCertainDayUseCase
) : AndroidViewModel(app) {

    suspend fun saveEvent(event: Event) : Long = saveEventUseCase.execute(event)

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