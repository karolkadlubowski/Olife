package com.example.olife.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class FakeNotesRepositoryTest {

    private var notesRepository=FakeNotesRepository()

    @Test
    fun saveNoteTest()= runBlocking {
        notesRepository.getSavedNotes().test {
            val emission = awaitItem()
            assertThat(emission).isEmpty()
        }
    }
}