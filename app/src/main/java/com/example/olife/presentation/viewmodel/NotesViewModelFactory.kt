package com.example.olife.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.olife.domain.usecase.DeleteNoteUseCase
import com.example.olife.domain.usecase.GetSavedNotesUseCase
import com.example.olife.domain.usecase.SaveNoteUseCase
import com.example.olife.domain.usecase.UpdateNoteUseCase

class NotesViewModelFactory(//connects with DI, in that case FactoryModule
    private val app: Application,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getSavedNotesUseCase: GetSavedNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(
            app,
            saveNoteUseCase,
            getSavedNotesUseCase,
            updateNoteUseCase,
            deleteNoteUseCase
        ) as T
    }

}
