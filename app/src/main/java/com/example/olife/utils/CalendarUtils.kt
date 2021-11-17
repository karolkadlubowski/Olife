package com.example.olife.utils

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

object CalendarUtils {
    public lateinit var selectedDate: LocalDate

    fun getFormattedDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH)
        return date.format(formatter)
    }

    fun monthYearFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        return date.format(formatter)
    }

    fun getDaysInMonthArray(date: LocalDate): ArrayList<LocalDate?> {
        var daysInMonthArray = ArrayList<LocalDate?>()
        var yearMonth: YearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstOfMonth = selectedDate.withDayOfMonth(1)
        var dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1 until dayOfWeek)
            daysInMonthArray.add(null)

        for (i in 1..daysInMonth) {
            daysInMonthArray.add(selectedDate.withDayOfMonth(i))
        }
        return daysInMonthArray
    }
}