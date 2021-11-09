package com.example.olife.presentation.viewmodel.note

import android.app.Application
import androidx.lifecycle.*
import com.example.olife.domain.usecase.note.DeleteNoteUseCase
import com.example.olife.domain.usecase.note.GetSavedNotesUseCase
import com.example.olife.domain.usecase.note.SaveNoteUseCase
import com.example.olife.domain.usecase.note.UpdateNoteUseCase

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
