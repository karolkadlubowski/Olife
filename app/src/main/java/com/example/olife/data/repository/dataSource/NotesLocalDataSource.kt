package com.example.olife.data.repository.dataSource

import com.example.olife.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {//connects with Impl
    suspend fun saveNoteToDB(note:Note)
    fun getSavedNotes(): Flow<List<Note>>
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}