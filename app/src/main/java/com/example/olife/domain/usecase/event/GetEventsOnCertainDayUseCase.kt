package com.example.olife.domain.usecase.event

import com.example.olife.data.model.Event
import com.example.olife.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetEventsOnCertainDayUseCase(private val eventsRepository: EventsRepository) {
    fun execute(localDate: LocalDate) : Flow<List<Event>> {
        return eventsRepository.getEventsOnCertainDay(localDate)
    }
}