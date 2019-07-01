package ru.skillbranch.devintensive.models

import java.util.*

class Chat

abstract class BaseMessage (
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String
    fun makeMessage(from: User, chat: Chat, date: Date, type: String, payload: String, isIncoming: Boolean = false) =
        when (type){
            "text" -> TextMessage("1", from, chat, isIncoming, date, payload)
            "image" -> ImageMessage("1", from, chat, isIncoming, date, payload)
            else -> null
        }
}


class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var payload: String
): BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return "$id: ${from?.firstName} ${if (isIncoming) "получил" else "отправил"} сообщение \"$payload\""
    }
}


class ImageMessage(
    id: String,
    from: User?,
    chat:  Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var payload: String
): BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return "$id: ${from?.firstName} ${if (isIncoming) "получил" else "отправил"} изображение \"$payload\""
    }
}

