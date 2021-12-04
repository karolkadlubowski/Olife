package com.example.olife.domain.di

import com.example.olife.domain.repository.EventsRepository
import com.example.olife.domain.repository.NotesRepository
import com.example.olife.domain.repository.VoiceNotesRepository
import com.example.olife.domain.usecase.event.*
import com.example.olife.domain.usecase.note.DeleteNoteUseCase
import com.example.olife.domain.usecase.note.GetSavedNotesUseCase
import com.example.olife.domain.usecase.note.SaveNoteUseCase
import com.example.olife.domain.usecase.note.UpdateNoteUseCase
import com.example.olife.domain.usecase.voiceNote.DeleteVoiceNoteUseCase
import com.example.olife.domain.usecase.voiceNote.GetSavedVoiceNotesUseCase
import com.example.olife.domain.usecase.voiceNote.SaveVoiceNoteUseCase
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
    fun provideSaveNoteUseCase(
        notesRepository:NotesRepository
    ): SaveNoteUseCase {
        return SaveNoteUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNotesUseCase(
        notesRepository: NotesRepository
    ): GetSavedNotesUseCase {
        return GetSavedNotesUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateNoteUseCase(
        notesRepository: NotesRepository
    ): UpdateNoteUseCase {
        return UpdateNoteUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteNoteUseCase(
        notesRepository: NotesRepository
    ) : DeleteNoteUseCase {
        return DeleteNoteUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideSaveVoiceNoteUseCase(
        voiceNotesRepository:VoiceNotesRepository
    ) : SaveVoiceNoteUseCase{
        return SaveVoiceNoteUseCase(voiceNotesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedVoiceNotesUseCase(
        voiceNotesRepository: VoiceNotesRepository
    ) : GetSavedVoiceNotesUseCase{
        return GetSavedVoiceNotesUseCase(voiceNotesRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteVoiceNoteUseCase(
        voiceNotesRepository: VoiceNotesRepository
    ) : DeleteVoiceNoteUseCase{
        return DeleteVoiceNoteUseCase(voiceNotesRepository)
    }

    @Singleton
    @Provides
    fun provideSaveEventUseCase(
        eventsRepository: EventsRepository
    ) : SaveEventUseCase{
        return SaveEventUseCase(eventsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedEventsUseCase(
        eventsRepository: EventsRepository
    ) : GetSavedEventsUseCase{
        return GetSavedEventsUseCase(eventsRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateEventUseCase(
        eventsRepository: EventsRepository
    ) : UpdateEventUseCase{
        return UpdateEventUseCase(eventsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteEventUseCase(
        eventsRepository: EventsRepository
    ) : DeleteEventUseCase{
        return DeleteEventUseCase(eventsRepository)
    }

    @Singleton
    @Provides
    fun provideGetEventsOnCertainDay(
        eventsRepository: EventsRepository
    ) : GetEventsOnCertainDayUseCase{
        return GetEventsOnCertainDayUseCase(eventsRepository)
    }

}