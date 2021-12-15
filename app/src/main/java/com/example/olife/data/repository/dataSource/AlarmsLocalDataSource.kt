package com.example.olife.data.repository.dataSource

import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow

interface AlarmsLocalDataSource {
    suspend fun saveAlarmToDB(alarm: Alarm) : Long
    fun getSavedAlarms() : Flow<List<Alarm>>
    suspend fun updateAlarm(alarm: Alarm)
    suspend fun deleteAlarm(alarm: Alarm)
}