package com.example.olife.domain.di

import android.app.Application
import androidx.room.Room
import com.example.olife.data.db.NoteDatabase
import com.example.olife.data.db.NoteDao
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
    fun provideNotesDatabase(app : Application) : NoteDatabase{
        return Room.databaseBuilder(app,NoteDatabase::class.java,"organiser_database")
            .fallbackToDestructiveMigration()// w razie konfliktów usuwa i tworzy tabele, uważać na to
            .build()
    }

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase) : NoteDao{
        return noteDatabase.getNoteDao()
    }
}