package com.example.olife.data.repository.dataSourceImpl

import android.util.Log
import com.example.olife.data.db.EventDao
import com.example.olife.data.model.Event
import com.example.olife.data.repository.dataSource.EventsLocalDataSource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class EventsLocalDataSourceImpl(private val eventDao: EventDao) : EventsLocalDataSource {
    override suspend fun saveEventToDB(event: Event) : Long = eventDao.insertEvent(event)

    override fun getSavedEvents(): Flow<List<Event>> {
        return eventDao.getAllEvents()
    }

    override suspend fun updateEvent(event: Event) {
        return eventDao.updateEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }

    override fun getEventsOnCertainDay(localDate: LocalDate): Flow<List<Event>> {
        return eventDao.getEventsOnCertainDay(localDate)
    }

}