package com.example.olife.domain.usecase.voiceNote

import com.example.olife.data.model.VoiceNote
import com.example.olife.domain.repository.VoiceNotesRepository

class DeleteVoiceNoteUseCase(private val voiceNotesRepository: VoiceNotesRepository)
{
    suspend fun execute(voiceNote: VoiceNote) = voiceNotesRepository.deleteVoiceNote(voiceNote)
}