package com.example.olife.domain.usecase.voiceNote

import com.example.olife.data.model.VoiceNote
import com.example.olife.domain.repository.VoiceNotesRepository
import kotlinx.coroutines.flow.Flow

class GetSavedVoiceNotesUseCase
    (
    private val voiceNotesRepository: VoiceNotesRepository
) {
        fun execute() : Flow<List<VoiceNote>> {
            return voiceNotesRepository.getSavedVoiceNotes()
        }
}