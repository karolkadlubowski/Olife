package com.example.olife.domain.di

import com.example.olife.domain.repository.NotesRepository
import com.example.olife.domain.usecase.DeleteNoteUseCase
import com.example.olife.domain.usecase.GetSavedNotesUseCase
import com.example.olife.domain.usecase.SaveNoteUseCase
import com.example.olife.domain.usecase.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {//connects with viewModel
    @Singleton
    @Provides
    fun provideSaveNotesUseCase(
        notesRepository:NotesRepository
    ): SaveNoteUseCase{
        return SaveNoteUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNotesUseCase(
        notesRepository: NotesRepository
    ): GetSavedNotesUseCase{
        return GetSavedNotesUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateNoteUseCase(
        notesRepository: NotesRepository
    ): UpdateNoteUseCase{
        return UpdateNoteUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteNoteUseCase(
        notesRepository: NotesRepository
    ) : DeleteNoteUseCase{
        return DeleteNoteUseCase(notesRepository)
    }
}