package com.example.olife.domain.di

import com.example.olife.data.repository.EventsRepositoryImpl
import com.example.olife.data.repository.NotesRepositoryImpl
import com.example.olife.data.repository.VoiceNotesRepositoryImpl
import com.example.olife.data.repository.dataSource.EventsLocalDataSource
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import com.example.olife.data.repository.dataSource.VoiceNotesLocalDataSource
import com.example.olife.domain.repository.EventsRepository
import com.example.olife.domain.repository.NotesRepository
import com.example.olife.domain.repository.VoiceNotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNotesRepository(
        notesLocalDataSource:NotesLocalDataSource
    ): NotesRepository{
        return NotesRepositoryImpl(notesLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideVoiceNotesRepository(
        voiceNotesLocalDataSource: VoiceNotesLocalDataSource
    ) : VoiceNotesRepository{
        return VoiceNotesRepositoryImpl(voiceNotesLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideEventsRepository(
        eventsLocalDataSource: EventsLocalDataSource
    ) : EventsRepository{
        return EventsRepositoryImpl(eventsLocalDataSource)
    }
}