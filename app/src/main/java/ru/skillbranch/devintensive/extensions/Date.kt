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


fun Date.humanizeDiff(date: Date = Date()): String{
    val diff = (this.time - date.time) / 1000
    val result: String
    when {
        diff in 0..1 -> result = "только что"
        diff in 1..45 -> result = "несколько секунд назад"
        diff in 45..75 -> result = "минуту назад"
        diff in 75..45*60 -> result = "${diff/60} минут назад"
        diff in 45*60..75*60 -> result = "час назад"
        diff in 75*60..22*3600 -> result = "${diff/60/60} часов назад"
        diff in 22*3600..26*3600 -> result = "день назад"
        diff in 26*3600..360*24*3600 -> result = "${diff/60/60/24} дней назад"
        diff > 360*24*3600 -> result = "более года назад"
        else -> result = ""
    }

    return result
}