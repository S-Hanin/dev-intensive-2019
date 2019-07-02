package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String{
    return SimpleDateFormat(pattern, Locale("ru")).format(this)
}


enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

fun Date.add(value: Int, Units: TimeUnits): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    when (Units){
        TimeUnits.SECOND -> cal.add(Calendar.SECOND, value)
        TimeUnits.MINUTE -> cal.add(Calendar.MINUTE, value)
        TimeUnits.HOUR -> cal.add(Calendar.HOUR, value)
        TimeUnits.DAY -> cal.add(Calendar.DATE, 1)
    }
    return cal.time
}