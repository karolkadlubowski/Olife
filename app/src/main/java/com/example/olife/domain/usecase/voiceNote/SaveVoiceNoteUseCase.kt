package com.example.olife.domain.usecase.voiceNote

import com.example.olife.data.model.VoiceNote
import com.example.olife.domain.repository.VoiceNotesRepository

class SaveVoiceNoteUseCase(
    private val voiceNotesRepository: VoiceNotesRepository
) {
    suspend fun execute(note:VoiceNote) = voiceNotesRepository.saveVoiceNote(note)
}