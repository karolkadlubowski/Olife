package com.example.olife.data.repository.dataSource

import com.example.olife.data.model.VoiceNote
import kotlinx.coroutines.flow.Flow

interface VoiceNotesLocalDataSource {
    suspend fun saveVoiceNoteToDB(voiceNote: VoiceNote)
    fun getSavedVoiceNotes() : Flow<List<VoiceNote>>
    suspend fun deleteVoiceNoteFromDB(voiceNote: VoiceNote)
}