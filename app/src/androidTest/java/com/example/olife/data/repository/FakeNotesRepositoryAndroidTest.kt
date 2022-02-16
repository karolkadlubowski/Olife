package com.example.olife.data.repository

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.olife.data.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class FakeNotesRepositoryAndroidTest {

    private var notesRepository=FakeNotesRepositoryAndroid()

    @Test
    fun rSaveNoteTest()= runBlocking {
        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            assertThat(emission).isEmpty()
        }

        notesRepository.saveNote(Note(1,"Note1","Description1"))

        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            Log.i("rSaveNoteTest",emission[0].title.toString())
            assertThat(emission).isNotEmpty()
        }
    }

    @Test
    fun rUpdateNoteTest() = runBlocking {
        notesRepository.saveNote(Note(1,"Note1","Description1"))
        notesRepository.updateNote(Note(1,"Note12","Description12"))
        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            Log.i("rSaveNoteTest",emission[0].title.toString())
            assertThat(emission.size).isEqualTo(1)
            assertThat(emission[0].id).isEqualTo(1)
            assertThat(emission[0].title).isEqualTo("Note12")
            assertThat(emission[0].content).isEqualTo("Description12")
        }
    }

    @Test
    fun rDeleteNoteTest()= runBlocking {
        notesRepository.saveNote(Note(1,"Note1","Description1"))

        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            Log.i("rSaveNoteTest",emission[0].title.toString())
            assertThat(emission).isNotEmpty()
        }

        notesRepository.deleteNote(Note(1,"Note1","Description1"))

        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            assertThat(emission).isEmpty()
        }
    }
}