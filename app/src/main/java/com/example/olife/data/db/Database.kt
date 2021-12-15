package com.example.olife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.olife.data.model.Alarm
import com.example.olife.data.model.Event
import com.example.olife.data.model.Note
import com.example.olife.data.model.VoiceNote

@Database(
    entities = [
        Note::class,
        VoiceNote::class,
        Event::class,
        Alarm::class
    ], version = 6, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getVoiceNoteDao() : VoiceNoteDao//provide it in di.DataBaseModule
    abstract fun getEventDao() : EventDao
    abstract fun getAlarmDao() : AlarmDao
}