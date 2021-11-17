package com.example.olife.data.model

import java.time.LocalDate
import java.time.LocalTime


data class Event (
    val id: Int?,
    var name : String?,
    var eventDate : LocalDate?,
    var eventTime : LocalTime?,
    var notificationDate : LocalDate?,
    var notificationTime : LocalTime?,
    var description : String?
        )