package com.example.olife.domain.usecase

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository

class SaveNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note:Note) = notesRepository.saveNote(note)
}