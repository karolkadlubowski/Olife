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
) : Serializable {
    /*override fun equals(other: Any?): Boolean {
        return this.id==(other as VoiceNote).id && this.name==(other as VoiceNote).name && this.memoryLocation==(other as VoiceNote).memoryLocation
    }*///should be overwritten if not data class
}