package com.example.olife.domain.repository

import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun saveEvent(event:Event)
    fun getSavedEvents() : Flow<List<Event>>
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(event: Event)
}