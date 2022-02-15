package com.example.olife.data.db

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.olife.data.model.Note
import com.example.olife.getOrAwaitValue
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


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: Database
    private lateinit var noteDao : NoteDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()
        noteDao = database.getNoteDao()
    }

    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun insertNoteTest()=runBlockingTest{
        val noteToInsert1 = Note(null,"SadNote","ContentSadNote")
        val noteToInsert2 = Note(null,"HappyNote","ContentHappyNote")
        noteDao.insertNote(noteToInsert1)
        noteDao.insertNote(noteToInsert2)
        val allNotes= liveData {
            noteDao.getAllNotes().collect {
                emit(it)
            }
        }.getOrAwaitValue()

        assertThat(allNotes[0].id).isEqualTo(1)
        assertThat(allNotes[0].title).isEqualTo("SadNote")
        assertThat(allNotes[1].id).isEqualTo(2)


        for( note in allNotes){
            Log.i("insertNoteTest", note.id!!.toString())
        }
        assertThat(allNotes).isNotEmpty()
        assertThat(allNotes.count()).isEqualTo(2)
    }


    @Test
    fun deleteNoteTest()= runBlockingTest {
        val noteToDelete = Note(1,"SadNote","ContentSadNote")

        val allNotesBeforeAdd= liveData {
            noteDao.getAllNotes().collect {
                emit(it)
            }
        }.getOrAwaitValue()

        Log.i("deleteNoteTest", allNotesBeforeAdd.count().toString())

        assertThat(allNotesBeforeAdd.count()).isEqualTo(0)

        noteDao.insertNote(noteToDelete)
        val allNotesAfterAdd= liveData {
            noteDao.getAllNotes().collect {
                emit(it)
            }
        }.getOrAwaitValue()
        Log.i("deleteNoteTest", allNotesAfterAdd.count().toString())

        assertThat(allNotesAfterAdd.count()).isEqualTo(1)

        noteDao.deleteNote(noteToDelete)

        val allNotesAfterDelete= liveData {
            noteDao.getAllNotes().collect {
                emit(it)
            }
        }.getOrAwaitValue()
        Log.i("deleteNoteTest", allNotesAfterDelete.count().toString())

        assertThat(allNotesAfterDelete.count()).isEqualTo(0)

    }

    @Test
    fun updateNoteTest() = runBlockingTest {
        val noteToInsert1 = Note(1,"SadNote","ContentSadNote")
        val noteToInsert2 = Note(2,"HappyNote","ContentHappyNote")

        noteDao.insertNote(noteToInsert1)
        noteDao.insertNote(noteToInsert2)

        val modulatedNoteToInsert2 = Note(2,"SoSoNote","ContentSoSoNote")

        noteDao.updateNote(modulatedNoteToInsert2)

        val allNotes= liveData {
            noteDao.getAllNotes().collect {
                emit(it)
            }
        }.getOrAwaitValue()

        for( note in allNotes){
            Log.i("updateNoteTest", note.id!!.toString())
            Log.i("updateNoteTest", note.title!!.toString())
        }

        assertThat(allNotes[0].id).isEqualTo(1)
        assertThat(allNotes[0].title).isEqualTo("SadNote")
        assertThat(allNotes[1].id).isEqualTo(2)
        assertThat(allNotes[1].title).isEqualTo("SoSoNote")

    }
}



class NoteDaoFlowTest{


    private lateinit var database: Database
    private lateinit var noteDao : NoteDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()
        noteDao = database.getNoteDao()
    }

    @After
    fun teardown(){
        database.close()
    }


    @Test
    fun getAllNotesTest() = runBlocking {

        val noteToInsert1 = Note(1,"SadNote","ContentSadNote")
        val noteToInsert2 = Note(2,"HappyNote","ContentHappyNote")

        noteDao.insertNote(noteToInsert1)
        noteDao.insertNote(noteToInsert2)

        noteDao.getAllNotes().test {
            val emission = awaitItem()
            assertThat(emission).isNotEmpty()
        }

    }
}