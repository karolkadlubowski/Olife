package com.example.olife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.olife.data.model.Note
import com.example.olife.data.model.VoiceNote

@Database(
    entities = [
        Note::class,
        VoiceNote::class
    ], version = 4, exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getVoiceNoteDao() : VoiceNoteDao//provide it in di.DataBaseModule
}