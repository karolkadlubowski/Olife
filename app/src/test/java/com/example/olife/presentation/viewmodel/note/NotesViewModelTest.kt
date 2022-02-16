package com.example.olife.presentation.viewmodel.note

import android.app.Application
import com.example.olife.data.repository.FakeNotesRepository
import com.example.olife.domain.usecase.note.DeleteNoteUseCase
import com.example.olife.domain.usecase.note.GetSavedNotesUseCase
import com.example.olife.domain.usecase.note.SaveNoteUseCase
import com.example.olife.domain.usecase.note.UpdateNoteUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class NotesViewModelTest{
    private lateinit var notesViewModel:NotesViewModel

    @Before
    fun setup(){
        notesViewModel = NotesViewModel(Application(),SaveNoteUseCase(FakeNotesRepository()),
            GetSavedNotesUseCase(FakeNotesRepository()),
            UpdateNoteUseCase(FakeNotesRepository()),
            DeleteNoteUseCase(FakeNotesRepository())
        )
    }

    @Test
    fun insertNote(){
        assertThat(true).isTrue()
    }
}