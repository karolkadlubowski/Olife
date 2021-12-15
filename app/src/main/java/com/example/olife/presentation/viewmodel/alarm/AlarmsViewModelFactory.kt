package com.example.olife.presentation.viewmodel.alarm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.olife.domain.usecase.alarm.DeleteAlarmUseCase
import com.example.olife.domain.usecase.alarm.GetSavedAlarmsUseCase
import com.example.olife.domain.usecase.alarm.SaveAlarmUseCase
import com.example.olife.domain.usecase.alarm.UpdateAlarmUseCase
import com.example.olife.domain.usecase.event.DeleteEventUseCase

class AlarmsViewModelFactory (
    private val app:Application,
    private val saveAlarmUseCase: SaveAlarmUseCase,
    private val getSavedAlarmsUseCase: GetSavedAlarmsUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
        ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlarmsViewModel(
            app,
            saveAlarmUseCase,
            getSavedAlarmsUseCase,
            updateAlarmUseCase,
            deleteAlarmUseCase
        ) as T
    }
}