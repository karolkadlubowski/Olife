package com.example.olife.domain.di

import com.example.olife.data.model.Note
import com.example.olife.presentation.adapter.AlarmsAdapter
import com.example.olife.presentation.adapter.EventsAdapter
import com.example.olife.presentation.adapter.NotesAdapter
import com.example.olife.presentation.adapter.VoiceNotesAdapter
import com.example.olife.presentation.viewmodel.alarm.AlarmsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNotesAdapter():NotesAdapter{
        return NotesAdapter()
    }

    @Singleton
    @Provides
    fun provideVoiceNotesAdapter():VoiceNotesAdapter{
        return VoiceNotesAdapter()
    }


    @Provides
    fun provideEventsAdapter():EventsAdapter{
        return EventsAdapter()
    }

    @Singleton
    @Provides
    fun provideAlarmsAdapter():AlarmsAdapter{
        return AlarmsAdapter()
    }
}