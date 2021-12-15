package com.example.olife.domain.di

import android.app.Application
import androidx.room.Room
import com.example.olife.data.db.*
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
            .fallbackToDestructiveMigration()// in case of conflicts, deletes and creates table
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: Database) : NoteDao{
        return database.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideVoiceNoteDao(database: Database) : VoiceNoteDao{
        return database.getVoiceNoteDao()
    }

    @Singleton
    @Provides
    fun provideEventDao(database: Database) : EventDao{
        return database.getEventDao()
    }

    @Singleton
    @Provides
    fun provideAlarmDao(database: Database) : AlarmDao{
        return database.getAlarmDao()
    }
}