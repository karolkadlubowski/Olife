package com.example.olife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.olife.data.model.Note

@Database(entities = [Note::class],version = 2,exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
/*
    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
 */
}