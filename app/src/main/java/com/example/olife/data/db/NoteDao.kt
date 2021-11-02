package com.example.olife.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.olife.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {//connects with dataSource
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)


    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}