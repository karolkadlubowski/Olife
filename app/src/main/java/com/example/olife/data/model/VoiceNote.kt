package com.example.olife.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "voice_notes"
)
data class VoiceNote(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String?,
    var memoryLocation: String?
) : Serializable