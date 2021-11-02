package com.example.olife.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.olife.data.model.Note
import com.example.olife.domain.usecase.DeleteNoteUseCase
import com.example.olife.domain.usecase.GetSavedNotesUseCase
import com.example.olife.domain.usecase.SaveNoteUseCase
import com.example.olife.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesViewModel(//change in viewModel = change in viewModelFactory
    private val app: Application,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getSavedNotesUseCase: GetSavedNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : AndroidViewModel(app) {

    fun saveNote(note: Note) = viewModelScope.launch {
        saveNoteUseCase.execute(note)
    }

    fun getSavedNotes() = liveData {
        getSavedNotesUseCase.execute().collect {
            emit(it)
        }
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        updateNoteUseCase.execute(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        deleteNoteUseCase.execute(note)
    }


}