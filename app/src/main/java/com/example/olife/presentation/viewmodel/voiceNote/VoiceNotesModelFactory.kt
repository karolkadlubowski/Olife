package com.example.olife.presentation.viewmodel.voiceNote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.olife.domain.usecase.voiceNote.DeleteVoiceNoteUseCase
import com.example.olife.domain.usecase.voiceNote.GetSavedVoiceNotesUseCase
import com.example.olife.domain.usecase.voiceNote.SaveVoiceNoteUseCase

class VoiceNotesModelFactory(
    private val app: Application,
    private val saveVoiceNoteUseCase: SaveVoiceNoteUseCase,
    private val getSavedVoiceNotesUseCase: GetSavedVoiceNotesUseCase,
    private val deleteVoiceNoteUseCase: DeleteVoiceNoteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VoiceNotesViewModel(
            app,
            saveVoiceNoteUseCase,
            getSavedVoiceNotesUseCase,
            deleteVoiceNoteUseCase
        ) as T
    }

}