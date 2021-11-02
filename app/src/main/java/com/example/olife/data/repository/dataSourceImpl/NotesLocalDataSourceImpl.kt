package com.example.olife.data.repository.dataSourceImpl

import com.example.olife.data.db.NoteDao
import com.example.olife.data.model.Note
import com.example.olife.data.repository.dataSource.NotesLocalDataSource
import kotlinx.coroutines.flow.Flow

class NotesLocalDataSourceImpl(private val noteDao: NoteDao) : NotesLocalDataSource {//connects with domain.repo
    override suspend fun saveNoteToDB(note: Note) {
        return noteDao.insertNote(note)
    }

    override fun getSavedNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}