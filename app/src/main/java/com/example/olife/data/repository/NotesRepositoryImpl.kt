package com.example.olife.data.repository

import com.example.olife.data.model.Note
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import com.example.olife.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(//connects with UseCase
    private val notesLocalDataSource: NotesLocalDataSource//provide it in di.LocalDataModule
) : NotesRepository {
    override suspend fun saveNote(note: Note) {
        notesLocalDataSource.saveNoteToDB(note)
    }

    override fun getSavedNotes(): Flow<List<Note>> {
        return notesLocalDataSource.getSavedNotes()
    }

    override suspend fun updateNote(note: Note) {
        return notesLocalDataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesLocalDataSource.deleteNote(note)
    }
}