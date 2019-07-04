package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String{
    return SimpleDateFormat(pattern, Locale("ru")).format(this)
}


enum class TimeUnits {
    SECOND{
        override fun plural(num: Int): String {
            return pluralForm(num, arrayOf("секунду", "секунды", "секунд"))
        }
    }, MINUTE{
        override fun plural(num: Int): String {
            return pluralForm(num, arrayOf("минуту", "минуты", "минут"))
        }
    }, HOUR{
        override fun plural(num: Int): String {
            return pluralForm(num, arrayOf("час", "часа", "часов"))
        }
    }, DAY{
        override fun plural(num: Int): String {
            return pluralForm(num, arrayOf("день", "дня", "дней"))
        }
    };

    abstract fun plural(num: Int): String
}

fun Date.add(value: Int, Units: TimeUnits): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    when (Units){
        TimeUnits.SECOND -> cal.add(Calendar.SECOND, value)
        TimeUnits.MINUTE -> cal.add(Calendar.MINUTE, value)
        TimeUnits.HOUR -> cal.add(Calendar.HOUR, value)
        TimeUnits.DAY -> cal.add(Calendar.DATE, value)
    }
    return cal.time
}


fun pluralForm(num: Int, after: Array<String>):String{
    val cases = arrayOf<Int>(2, 0, 1, 1, 1, 2)
    val case = if (num%100 in 5..19) 2 else cases[min(num%10,5)]
    return "$num ${after[case]}"
}


fun Date.humanizeDiff(date: Date = Date()): String{
    val diff = (this.time - date.time) / 1000
    val result: String
    when {
        diff in -1..0 -> result = "только что"
        diff in -45..-1 -> result = "несколько секунд назад"
        diff in -75..-45 -> result = "минуту назад"
        diff in -45*60..-75 -> result = "${pluralForm((Math.abs(diff)/60).toInt(), arrayOf("минута", "минуты", "минут"))} назад"
        diff in -75*60..-45*60 -> result = "час назад"
        diff in -22*3600..-75*60 -> result = "${pluralForm((Math.abs(diff)/60/60).toInt(), arrayOf("час", "часа", "часов"))} назад"
        diff in -26*3600..-22*3600 -> result = "день назад"
        diff in -360*24*3600..-26*3600 -> result = "${pluralForm((Math.abs(diff)/60/60/24).toInt(), arrayOf("день", "дня", "дней"))} назад"
        diff < -360*24*3600 -> result = "более года назад"

        diff in 0..1 -> result = "уже вот"
        diff in 1..45 -> result = "через несколько секунд"
        diff in 45..75 -> result = "через минуту"
        diff in 75..45*60 -> result = "через ${pluralForm((Math.abs(diff)/60).toInt(), arrayOf("минута", "минуты", "минут"))}"
        diff in 45*60..75*60 -> result = "через час"
        diff in 75*60..22*3600 -> result = "через ${pluralForm((Math.abs(diff)/60/60).toInt(), arrayOf("час", "часа", "часов"))}"
        diff in 22*3600..26*3600 -> result = "через день"
        diff in 26*3600..360*24*3600 -> result = "через ${pluralForm((Math.abs(diff)/60/60/24).toInt(), arrayOf("день", "дня", "дней"))}"
        diff > 360*24*3600 -> result = "более чем через год"
        else -> result = ""
    }

    return result
}