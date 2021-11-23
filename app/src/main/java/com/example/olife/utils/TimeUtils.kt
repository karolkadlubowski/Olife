package com.example.olife.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtils {
    fun getStringFromLocalTime(time: LocalTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
        return time.format(formatter)
    }

    fun getLocalTimeFromString(timeString:String) : LocalTime{
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
        return LocalTime.parse(timeString,formatter)
    }
}