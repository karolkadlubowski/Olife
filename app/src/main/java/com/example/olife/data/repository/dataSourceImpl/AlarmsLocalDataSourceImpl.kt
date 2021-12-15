package com.example.olife.data.repository.dataSourceImpl

import com.example.olife.data.db.AlarmDao
import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import com.example.olife.data.repository.dataSource.AlarmsLocalDataSource
import kotlinx.coroutines.flow.Flow

class AlarmsLocalDataSourceImpl(private val alarmDao: AlarmDao) : AlarmsLocalDataSource {
    override suspend fun saveAlarmToDB(alarm: Alarm) : Long = alarmDao.insert(alarm)

    override fun getSavedAlarms(): Flow<List<Alarm>> = alarmDao.getAll()

    override suspend fun updateAlarm(alarm: Alarm) = alarmDao.delete(alarm)

    override suspend fun deleteAlarm(alarm: Alarm) = alarmDao.delete(alarm)
}