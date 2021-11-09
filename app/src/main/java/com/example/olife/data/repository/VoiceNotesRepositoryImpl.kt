package com.example.olife.data.repository

import com.example.olife.data.model.VoiceNote
import com.example.olife.data.repository.dataSource.VoiceNotesLocalDataSource
import com.example.olife.domain.repository.VoiceNotesRepository
import kotlinx.coroutines.flow.Flow

class VoiceNotesRepositoryImpl(
    private val voiceNotesLocalDataSource: VoiceNotesLocalDataSource
) : VoiceNotesRepository{
    override suspend fun saveVoiceNote(voiceNote: VoiceNote) {
        voiceNotesLocalDataSource.saveVoiceNoteToDB(voiceNote)
    }

    override fun getSavedVoiceNotes(): Flow<List<VoiceNote>> {
        return voiceNotesLocalDataSource.getSavedVoiceNotes()
    }

    override suspend fun deleteVoiceNote(voiceNote: VoiceNote) {
        voiceNotesLocalDataSource.deleteVoiceNoteFromDB(voiceNote)
    }
}