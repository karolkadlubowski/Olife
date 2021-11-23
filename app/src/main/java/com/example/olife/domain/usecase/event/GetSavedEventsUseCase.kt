package com.example.olife.domain.usecase.event

import com.example.olife.data.model.Event
import com.example.olife.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedEventsUseCase(private val eventsRepository: EventsRepository) {
    fun execute() : Flow<List<Event>> {
        return eventsRepository.getSavedEvents()
    }
}