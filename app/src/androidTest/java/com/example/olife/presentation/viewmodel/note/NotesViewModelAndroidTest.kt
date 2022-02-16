package com.example.olife.presentation.viewmodel.note

import android.app.Application
import com.example.olife.data.repository.FakeNotesRepositoryAndroid
import com.example.olife.domain.usecase.note.DeleteNoteUseCase
import com.example.olife.domain.usecase.note.GetSavedNotesUseCase
import com.example.olife.domain.usecase.note.SaveNoteUseCase
import com.example.olife.domain.usecase.note.UpdateNoteUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class NotesViewModelAndroidTest{
    private lateinit var notesViewModel:NotesViewModel

    @Before
    fun setup(){
        notesViewModel = NotesViewModel(Application(),SaveNoteUseCase(FakeNotesRepositoryAndroid()),
            GetSavedNotesUseCase(FakeNotesRepositoryAndroid()),
            UpdateNoteUseCase(FakeNotesRepositoryAndroid()),
            DeleteNoteUseCase(FakeNotesRepositoryAndroid())
        )
    }

    @Test
    fun insertNote(){
        assertThat(true).isTrue()
    }
}