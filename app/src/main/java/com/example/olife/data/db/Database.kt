package com.example.olife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.olife.data.model.Note
import com.example.olife.data.model.Recording

@Database(
    entities = [
        Note::class,
        Recording::class
    ], version = 3, exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}