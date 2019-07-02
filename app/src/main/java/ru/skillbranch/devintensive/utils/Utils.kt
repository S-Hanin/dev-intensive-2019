package ru.skillbranch.devintensive.utils


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


fun transliteration(payload: String, divider: String = " "):String{
    val pairs = mapOf(
        "а" to "a", "б" to "b", "в" to "v", "г" to "g",
        "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh",
        "з" to "z", "и" to "i", "й" to "i", "к" to "k",
        "л" to "l", "м" to "m", "н" to "n", "о" to "o",
        "п" to "p", "р" to "r", "с" to "s", "т" to "t",
        "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
        "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "",
        "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu",
        "я" to "ya", " " to divider,
        "А" to "A", "Б" to "B", "В" to "V", "Г" to "G",
        "Д" to "D", "Е" to "E", "Ё" to "E", "Ж" to "Zh",
        "З" to "Z", "И" to "I", "Й" to "I", "К" to "K",
        "Л" to "L", "М" to "M", "Н" to "N", "О" to "O",
        "П" to "P", "Р" to "R", "С" to "S", "Т" to "T",
        "У" to "U", "Ф" to "F", "Х" to "H", "Ц" to "C",
        "Ч" to "Ch", "Ш" to "Sh", "Щ" to "Sh'", "Ъ" to "",
        "Ы" to "I", "Ь" to "", "Э" to "E", "Ю" to "Yu",
        "Я" to "Ya")

    val builder = StringBuilder()
    for (item in payload){
        builder.append(
                if (pairs.containsKey(item.toString())) pairs[item.toString()]
                else item.toString()
        )
    }
    return builder.toString()
}
