package com.example.olife.domain.usecase.event

import com.example.olife.data.model.Event
import com.example.olife.data.model.Note
import com.example.olife.domain.repository.EventsRepository

class SaveEventUseCase(private val eventsRepository: EventsRepository) {
    suspend fun execute(event: Event) = eventsRepository.saveEvent(event)
}