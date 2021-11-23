package com.example.olife.domain.di

import com.example.olife.data.db.EventDao
import com.example.olife.data.db.NoteDao
import com.example.olife.data.db.VoiceNoteDao
import com.example.olife.data.model.VoiceNote
import com.example.olife.data.repository.dataSource.EventsLocalDataSource
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import com.example.olife.data.repository.dataSource.VoiceNotesLocalDataSource
import com.example.olife.data.repository.dataSourceImpl.EventsLocalDataSourceImpl
import com.example.olife.data.repository.dataSourceImpl.NotesLocalDataSourceImpl
import com.example.olife.data.repository.dataSourceImpl.VoiceNotesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideNotesLocalDataSource(noteDao: NoteDao) : NotesLocalDataSource{
        return NotesLocalDataSourceImpl(noteDao)
    }

    @Singleton
    @Provides
    fun provideVoiceNotesLocalDataSource(voiceNoteDao:VoiceNoteDao) : VoiceNotesLocalDataSource{
        return VoiceNotesLocalDataSourceImpl(voiceNoteDao)
    }

    @Singleton
    @Provides
    fun provideEventsLocalDataSource(eventDao: EventDao) : EventsLocalDataSource{
        return EventsLocalDataSourceImpl(eventDao)
    }
}