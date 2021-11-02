package com.example.olife.domain.usecase

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository

class UpdateNoteUseCase(private val notesRepository:NotesRepository) { //connects with DI,in that case UseCaseModule
    suspend fun execute(note: Note) = notesRepository.updateNote(note)
}