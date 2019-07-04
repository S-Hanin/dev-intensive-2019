package ru.skillbranch.devintensive.models
import java.util.*


abstract class BaseMessage (
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object Factory{
        fun makeMessage(from: User, chat: Chat, date: Date, type: String, payload: String, isIncoming: Boolean = false) =
            when (type){
                "text" -> TextMessage("1", from, chat, isIncoming, date, payload)
                "image" -> ImageMessage("1", from, chat, isIncoming, date, payload)
                else -> TextMessage("1", from, chat, isIncoming, date, payload)
            }
    }
}
