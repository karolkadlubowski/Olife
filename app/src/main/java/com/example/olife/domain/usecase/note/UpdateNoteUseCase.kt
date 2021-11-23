package com.example.olife.domain.usecase.note

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository

class UpdateNoteUseCase(
    private val notesRepository:NotesRepository //provide it in DI.RepositoryModule
) { //connects with viewmodel
    suspend fun execute(note: Note) = notesRepository.updateNote(note)
}