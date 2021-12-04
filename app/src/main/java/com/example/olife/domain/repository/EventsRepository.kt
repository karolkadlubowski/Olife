package com.example.olife.domain.repository

import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EventsRepository {
    suspend fun saveEvent(event:Event) : Long
    fun getSavedEvents() : Flow<List<Event>>
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    fun getEventsOnCertainDay(localDate : LocalDate) : Flow<List<Event>>
}