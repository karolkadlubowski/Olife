package com.example.olife.data.repository

import com.example.olife.data.model.Event
import com.example.olife.data.repository.dataSource.EventsLocalDataSource
import com.example.olife.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class EventsRepositoryImpl(
    private val eventsLocalDataSource : EventsLocalDataSource
) : EventsRepository {
    override suspend fun saveEvent(event: Event) {
        eventsLocalDataSource.saveEventToDB(event)
    }

    override fun getSavedEvents(): Flow<List<Event>> {
        return eventsLocalDataSource.getSavedEvents()
    }

    override suspend fun updateEvent(event: Event) {
        eventsLocalDataSource.updateEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventsLocalDataSource.deleteEvent(event)
    }
}