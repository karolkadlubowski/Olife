package com.example.olife.domain.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.olife.domain.usecase.event.DeleteEventUseCase
import com.example.olife.domain.usecase.event.GetSavedEventsUseCase
import com.example.olife.domain.usecase.event.SaveEventUseCase
import com.example.olife.domain.usecase.event.UpdateEventUseCase
import com.example.olife.domain.usecase.note.DeleteNoteUseCase
import com.example.olife.domain.usecase.note.GetSavedNotesUseCase
import com.example.olife.domain.usecase.note.SaveNoteUseCase
import com.example.olife.domain.usecase.note.UpdateNoteUseCase
import com.example.olife.domain.usecase.voiceNote.DeleteVoiceNoteUseCase
import com.example.olife.domain.usecase.voiceNote.GetSavedVoiceNotesUseCase
import com.example.olife.domain.usecase.voiceNote.SaveVoiceNoteUseCase
import com.example.olife.presentation.viewmodel.event.EventsViewModelFactory
import com.example.olife.presentation.viewmodel.note.NotesViewModelFactory
import com.example.olife.presentation.viewmodel.voiceNote.VoiceNotesModelFactory
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
        savedNoteUseCase: SaveNoteUseCase,
        getSavedNotesUseCase: GetSavedNotesUseCase,
        updateNoteUseCase: UpdateNoteUseCase,
        deleteNoteUseCase: DeleteNoteUseCase
    ) : NotesViewModelFactory {
        return NotesViewModelFactory(application,savedNoteUseCase,getSavedNotesUseCase,updateNoteUseCase,deleteNoteUseCase)
    }

    @Singleton
    @Provides
    fun provideVoiceNotesViewModelFactory(
        application: Application,
        saveVoiceNoteUseCase: SaveVoiceNoteUseCase,
        getSavedVoiceNotesUseCase: GetSavedVoiceNotesUseCase,
        deleteVoiceNoteUseCase: DeleteVoiceNoteUseCase
    ) : VoiceNotesModelFactory{
        return VoiceNotesModelFactory(application,saveVoiceNoteUseCase,getSavedVoiceNotesUseCase,deleteVoiceNoteUseCase)
    }

    @Singleton
    @Provides
    fun provideEventsViewModelFactory(
        application: Application,
        saveEventUseCase: SaveEventUseCase,
        getSavedEventsUseCase: GetSavedEventsUseCase,
        updateEventUseCase: UpdateEventUseCase,
        deleteEventUseCase: DeleteEventUseCase
    ) : EventsViewModelFactory{
        return EventsViewModelFactory(application,saveEventUseCase, getSavedEventsUseCase, updateEventUseCase, deleteEventUseCase)
    }
}