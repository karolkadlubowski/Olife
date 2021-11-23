package com.example.olife.data.db

import androidx.room.TypeConverter
import com.example.olife.utils.CalendarUtils
import com.example.olife.utils.TimeUtils
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    private val calendarUtils = CalendarUtils
    private val timeUtils = TimeUtils

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate) : String{
        return calendarUtils.getStringFromLocalDate(localDate)
    }

    @TypeConverter
    fun toLocalDate(dateString:String) : LocalDate{
        return calendarUtils.getLocalDateFromString(dateString)
    }

    @TypeConverter
    fun fromLocalTime(localTime: LocalTime) : String{
        return timeUtils.getStringFromLocalTime(localTime)
    }

    @TypeConverter
    fun toLocalTime(timeString: String) : LocalTime{
        return timeUtils.getLocalTimeFromString(timeString)
    }
}