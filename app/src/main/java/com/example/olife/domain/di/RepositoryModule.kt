package com.example.olife.domain.di

import com.example.olife.data.repository.NotesRepositoryImpl
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import com.example.olife.domain.repository.NotesRepository
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
}