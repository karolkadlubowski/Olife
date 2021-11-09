package com.example.olife.domain.usecase.note

import com.example.olife.data.model.Note
import com.example.olife.domain.repository.NotesRepository

class SaveNoteUseCase(
    private val notesRepository: NotesRepository//provide it in DI.RepositoryModule
    ) {
    suspend fun execute(note:Note) = notesRepository.saveNote(note)
}