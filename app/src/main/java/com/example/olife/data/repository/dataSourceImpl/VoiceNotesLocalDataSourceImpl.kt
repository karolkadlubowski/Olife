package com.example.olife.data.repository.dataSourceImpl

import com.example.olife.data.db.VoiceNoteDao
import com.example.olife.data.model.VoiceNote
import com.example.olife.data.repository.dataSource.VoiceNotesLocalDataSource
import kotlinx.coroutines.flow.Flow

class VoiceNotesLocalDataSourceImpl(
    private val voiceNoteDao: VoiceNoteDao
) : VoiceNotesLocalDataSource {
    override suspend fun saveVoiceNoteToDB(voiceNote: VoiceNote) {
        voiceNoteDao.insert(voiceNote)
    }

    override fun getSavedVoiceNotes(): Flow<List<VoiceNote>> {
        return voiceNoteDao.getAllVoiceNotes()
    }

    override suspend fun deleteVoiceNoteFromDB(voiceNote: VoiceNote) {
        return voiceNoteDao.deleteVoiceNote(voiceNote)
    }
}