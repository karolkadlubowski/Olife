package com.example.olife.data.repository.dataSourceImpl

import com.example.olife.data.db.EventDao
import com.example.olife.data.model.Event
import com.example.olife.data.repository.dataSource.EventsLocalDataSource
import kotlinx.coroutines.flow.Flow

class EventsLocalDataSourceImpl(private val eventDao: EventDao) : EventsLocalDataSource {
    override suspend fun saveEventToDB(event: Event) {
        return eventDao.insertEvent(event)
    }

    override fun getSavedEvents(): Flow<List<Event>> {
        return eventDao.getAllEvents()
    }

    override suspend fun updateEvent(event: Event) {
        return eventDao.updateEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }

}