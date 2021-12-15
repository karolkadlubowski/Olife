package com.example.olife.presentation.viewmodel.alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import com.example.olife.domain.usecase.alarm.DeleteAlarmUseCase
import com.example.olife.domain.usecase.alarm.GetSavedAlarmsUseCase
import com.example.olife.domain.usecase.alarm.SaveAlarmUseCase
import com.example.olife.domain.usecase.alarm.UpdateAlarmUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlarmsViewModel(
    private val app:Application,
    private val saveAlarmUseCase: SaveAlarmUseCase,
    private val getSavedAlarmsUseCase: GetSavedAlarmsUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
) : AndroidViewModel(app){

    suspend fun saveAlarm(alarm:Alarm) : Long = saveAlarmUseCase.execute(alarm)

    fun getSavedAlarms() = liveData {
        getSavedAlarmsUseCase.execute().collect {
            emit(it)
        }
    }

    fun updateAlarm(alarm: Alarm) = viewModelScope.launch {
        updateAlarmUseCase.execute(alarm)
    }

    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch {
        deleteAlarmUseCase.execute(alarm)
    }
}