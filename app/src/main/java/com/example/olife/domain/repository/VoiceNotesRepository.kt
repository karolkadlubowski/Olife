package com.example.olife.domain.repository

import com.example.olife.data.model.VoiceNote
import kotlinx.coroutines.flow.Flow

interface VoiceNotesRepository {
    suspend fun saveVoiceNote(voiceNote:VoiceNote)
    fun getSavedVoiceNotes() : Flow<List<VoiceNote>>
    suspend fun deleteVoiceNote(voiceNote: VoiceNote)
}