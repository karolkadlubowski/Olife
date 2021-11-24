package com.example.olife.data.repository.dataSource

import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EventsLocalDataSource {
    suspend fun saveEventToDB(event:Event)
    fun getSavedEvents() : Flow<List<Event>>
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    fun getEventsOnCertainDay(localDate : LocalDate) : Flow<List<Event>>
}