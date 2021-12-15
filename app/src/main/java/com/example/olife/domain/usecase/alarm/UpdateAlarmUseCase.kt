package com.example.olife.domain.usecase.alarm

import com.example.olife.data.model.Alarm
import com.example.olife.domain.repository.AlarmsRepository

class UpdateAlarmUseCase(private val alarmsRepository: AlarmsRepository) {
    suspend fun execute(alarm:Alarm) = alarmsRepository.updateAlarm(alarm)
}