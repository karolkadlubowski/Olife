package com.example.olife.domain.usecase.note

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository

class DeleteNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note: Note) = notesRepository.deleteNote(note)
}