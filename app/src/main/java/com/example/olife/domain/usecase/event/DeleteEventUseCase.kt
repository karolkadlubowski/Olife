package com.example.olife.domain.usecase.event

import com.example.olife.data.model.Event
import com.example.olife.domain.repository.EventsRepository

class DeleteEventUseCase(private val eventsRepository: EventsRepository) {
    suspend fun execute(event:Event) = eventsRepository.deleteEvent(event)
}