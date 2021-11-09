package com.example.olife.presentation.viewmodel.voiceNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.olife.data.model.VoiceNote
import com.example.olife.domain.usecase.voiceNote.DeleteVoiceNoteUseCase
import com.example.olife.domain.usecase.voiceNote.GetSavedVoiceNotesUseCase
import com.example.olife.domain.usecase.voiceNote.SaveVoiceNoteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.nio.file.Files
import java.nio.file.Paths

class VoiceNotesViewModel(
    private val app: Application,
    private val saveVoiceNoteUseCase: SaveVoiceNoteUseCase,
    private val getSavedVoiceNotesUseCase: GetSavedVoiceNotesUseCase,
    private val deleteVoiceNoteUseCase: DeleteVoiceNoteUseCase
) : AndroidViewModel(app) {

    fun saveVoiceNote(voiceNote:VoiceNote) = viewModelScope.launch {
        saveVoiceNoteUseCase.execute(voiceNote)
    }

    fun getSavedVoiceNotes() = liveData {
        getSavedVoiceNotesUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteVoiceNote(voiceNote: VoiceNote) = viewModelScope.launch {
        Files.delete(Paths.get(voiceNote.memoryLocation))
        deleteVoiceNoteUseCase.execute(voiceNote)
    }

}