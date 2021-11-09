package com.example.olife.domain.di

import android.app.Application
import androidx.room.Room
import com.example.olife.data.db.Database
import com.example.olife.data.db.NoteDao
import com.example.olife.data.db.VoiceNoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app : Application) : Database{
        return Room.databaseBuilder(app,Database::class.java,"organiser_database")
            .fallbackToDestructiveMigration()// w razie konfliktów usuwa i tworzy tabele, uważać na to
            .build()
    }

    @Singleton
    @Provides
    fun provideNotesDao(database: Database) : NoteDao{
        return database.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideVoiceNotesDao(database: Database) : VoiceNoteDao{
        return database.getVoiceNoteDao()
    }
}