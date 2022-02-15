package com.example.olife.data.db

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.olife.data.model.Event
import com.example.olife.data.model.Note
import com.example.olife.getOrAwaitValue
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.LocalTime


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi//mogą być ale nie muszą ?
@SmallTest
class EventDaoTest{

    private lateinit var database: Database
    private lateinit var eventDao: EventDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()
        eventDao = database.getEventDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertEventTest()= runBlocking{
        val eventToInsert = Event(1,"Lekarz", LocalDate.now(), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)
        val insertedEventID = eventDao.insertEvent(eventToInsert)
        Log.i("insertEventTest", insertedEventID.toString())
        eventDao.getAllEvents().test {
            val emission = awaitItem()
            assertThat(emission).isNotEmpty()
        }
    }

    @Test
    fun getAllEventsTest()=runBlocking{
        eventDao.getAllEvents().test {
            val emission = awaitItem()
            assertThat(emission).isEmpty()
        }
    }

    @Test
    fun updateEventTest() = runBlocking {
        val eventToInsert = Event(1,"Lekarz", LocalDate.now(), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)
        eventDao.insertEvent(eventToInsert)
        val eventToUpdate = eventToInsert.copy()
        eventToUpdate.name="Doctor"
        Log.i("updateEventTest", eventToUpdate.name!!)
        eventDao.updateEvent(eventToUpdate)
        eventDao.getAllEvents().test {
            val emission = awaitItem()
            assertThat(emission[0].id).isEqualTo(1)
            assertThat(emission[0].name).isEqualTo("Doctor")

        }
    }

    @Test
    fun deleteEventTest() = runBlocking {
        val eventToDelete = Event(1,"Lekarz", LocalDate.now(), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)

        eventDao.insertEvent(eventToDelete)
        eventDao.deleteEvent(eventToDelete)
        eventDao.getAllEvents().test {
            val emission = awaitItem()
            assertThat(emission).isEmpty()
        }
    }

    @Test
    fun getEventsOnCertainDay() = runBlocking{
        val eventToday1 = Event(null,"Doctor", LocalDate.now(), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)
        val eventToday2 = Event(null,"Shopping", LocalDate.now(), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)
        val eventYesterday1 = Event(null,"Meeting", LocalDate.now().plusDays(1), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)
        val eventTomorrow1 = Event(null,"Movie", LocalDate.now().minusDays(1), LocalTime.of(10,20),LocalDate.now(),LocalTime.now(),null)

        eventDao.insertEvent(eventToday2)
        eventDao.insertEvent(eventYesterday1)
        eventDao.insertEvent(eventTomorrow1)
        eventDao.insertEvent(eventToday1)

        eventDao.getEventsOnCertainDay(LocalDate.now()).test {
            val emission = awaitItem()
            assertThat(emission.count()).isEqualTo(2)
        }
    }

}