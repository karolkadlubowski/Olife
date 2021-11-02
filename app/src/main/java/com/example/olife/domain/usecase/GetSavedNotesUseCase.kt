package com.example.olife.domain.usecase

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNotesUseCase(private val notesRepository: NotesRepository) {
    fun execute(): Flow<List<Note>>{
        return notesRepository.getSavedNotes()
    }
}