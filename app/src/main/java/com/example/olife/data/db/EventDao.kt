package com.example.olife.data.db

import androidx.room.*
import com.example.olife.data.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event:Event) : Long

    @Query("SELECT * FROM events ORDER BY id ASC")
    fun getAllEvents(): Flow<List<Event>>

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM events WHERE eventDate = :localDate ORDER BY eventTime ASC")
    fun getEventsOnCertainDay(localDate : LocalDate) : Flow<List<Event>>

    @Query("SELECT * FROM events WHERE   strftime('%s',eventDate) BETWEEN strftime('%s', :localDateFrom) AND strftime('%s', :localDateTo) ORDER BY eventDate,eventTime ASC")
    fun getEventsAtCurrentWeek(localDateFrom: LocalDate,localDateTo: LocalDate) : Flow<List<Event>>
}