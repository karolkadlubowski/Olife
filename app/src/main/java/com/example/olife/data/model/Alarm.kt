package com.example.olife.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalTime

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title : String?,
    var time : LocalTime?,
    var repeat : Boolean?,
    var soundMemoryLocation : String?,
    var isTurnedOn : Boolean?
) : Serializable