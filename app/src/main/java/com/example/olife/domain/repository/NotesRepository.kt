package com.example.olife.domain.repository

import com.example.olife.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository { //connects with db.RepoImpl
    suspend fun saveNote(note: Note)
    fun getSavedNotes(): Flow<List<Note>>
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}