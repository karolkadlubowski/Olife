package com.example.olife.domain.di

import android.app.Application
import com.example.olife.domain.usecase.DeleteNoteUseCase
import com.example.olife.domain.usecase.GetSavedNotesUseCase
import com.example.olife.domain.usecase.SaveNoteUseCase
import com.example.olife.domain.usecase.UpdateNoteUseCase
import com.example.olife.presentation.viewmodel.NotesViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNotesViewModelFactory(
        application: Application,
        savedNotesUseCase: SaveNoteUseCase,
        getSavedNotesUseCase: GetSavedNotesUseCase,
        updateNoteUseCase: UpdateNoteUseCase,
        deleteNoteUseCase: DeleteNoteUseCase
    ) : NotesViewModelFactory {
        return NotesViewModelFactory(application,savedNotesUseCase,getSavedNotesUseCase,updateNoteUseCase,deleteNoteUseCase)
    }
}