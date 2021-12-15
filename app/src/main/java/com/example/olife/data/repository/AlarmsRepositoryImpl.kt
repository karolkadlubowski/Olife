package com.example.olife.data.repository

import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import com.example.olife.data.repository.dataSource.AlarmsLocalDataSource
import com.example.olife.domain.repository.AlarmsRepository
import kotlinx.coroutines.flow.Flow

class AlarmsRepositoryImpl
    (private val alarmsLocalDataSource: AlarmsLocalDataSource) : AlarmsRepository {
    override suspend fun saveAlarm(alarm: Alarm): Long = alarmsLocalDataSource.saveAlarmToDB(alarm)

    override fun getSavedAlarms(): Flow<List<Alarm>> = alarmsLocalDataSource.getSavedAlarms()

    override suspend fun updateAlarm(alarm: Alarm) = alarmsLocalDataSource.updateAlarm(alarm)

    override suspend fun deleteAlarm(alarm: Alarm)= alarmsLocalDataSource.deleteAlarm(alarm)

}