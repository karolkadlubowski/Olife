package com.example.olife.domain.di

import com.example.olife.data.db.NoteDao
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import com.example.olife.data.repository.dataSourceImpl.NotesLocalDataSourceImpl
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
    fun provideLocalDataSource(noteDao: NoteDao) : NotesLocalDataSource{
        return NotesLocalDataSourceImpl(noteDao)
    }
}