package com.example.olife.domain.repository

import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow

interface AlarmsRepository {
    suspend fun saveAlarm(alarm:Alarm) :Long
    fun getSavedAlarms() : Flow<List<Alarm>>
    suspend fun updateAlarm(alarm: Alarm)
    suspend fun deleteAlarm(alarm: Alarm)
}