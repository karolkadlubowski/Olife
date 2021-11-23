package com.example.olife.data.db

import androidx.room.*
import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event:Event)

    @Query("SELECT * FROM events ORDER BY id ASC")
    fun getAllEvents(): Flow<List<Event>>

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)
}