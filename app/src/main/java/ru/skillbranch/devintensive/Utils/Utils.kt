package ru.skillbranch.devintensive.Utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun parseFullName(fullName: String?):Pair<String?, String?>{
    if (fullName?.trim() == "") return null to null
    val splited = fullName?.split(" ")
    val firstName = splited?.getOrNull(0)
    val lastName = splited?.getOrNull(1)
    return firstName to lastName
}


fun toInitials(firstName: String?, lastName: String?): String?{
    val first = firstName?.trim()?.capitalize()?.getOrNull(0)
    val last = lastName?.trim()?.capitalize()?.getOrNull(0)
    when {
        (first == null && last == null) -> return null
        first == null -> return last.toString()
        last == null -> return first.toString()
    }
    return String(charArrayOf(first!!, last!!))
}


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String{
    return SimpleDateFormat(pattern, Locale("ru")).format(this)
}


enum class TimeUnits {
SECOND, MINUTE, HOUR, DAY
}

fun Date.add(value: Int, Units: TimeUnits):Date {
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


fun transliteration(payload: String, divider: String = " "):String{
    var pairs = mapOf<String, String>(
        "а" to "a", "б" to "b", "в" to "v", "г" to "g",
        "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh",
        "з" to "z", "и" to "i", "й" to "i", "к" to "k",
        "л" to "l", "м" to "m", "н" to "n", "о" to "o",
        "п" to "p", "р" to "r", "с" to "s", "т" to "t",
        "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
        "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "",
        "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu",
        "я" to "ya", " " to divider)

    for (item in payload){}

    return ""
}
