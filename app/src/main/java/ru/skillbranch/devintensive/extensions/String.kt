package ru.skillbranch.devintensive.extensions

fun String.stripHtml():String{
    return replace(Regex("<.*?>"), "")
            .replace(Regex("\\s+"), " ")
}