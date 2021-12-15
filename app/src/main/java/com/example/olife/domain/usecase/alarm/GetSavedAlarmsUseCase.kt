package com.example.olife.domain.usecase.alarm

import com.example.olife.data.model.Alarm
import com.example.olife.domain.repository.AlarmsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedAlarmsUseCase(private val alarmsRepository: AlarmsRepository) {
    fun execute() : Flow<List<Alarm>> = alarmsRepository.getSavedAlarms()
}