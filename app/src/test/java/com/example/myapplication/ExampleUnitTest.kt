package com.example.myapplication

import org.junit.Test
import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.extensions.stripHtml
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils

import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_makeMessage() {
        assertEquals("Василий отправил сообщение \"any text message\" только что",
                BaseMessage.makeMessage(User.makeUser("Василий"), Chat("1"), Date(), "any text message", "text").formatMessage())
        assertEquals("Василий получил изображение \"https://anyurl.com\" 2 часа назад",
                BaseMessage.makeMessage(User.makeUser("Василий"), Chat("1"), Date().add(-2, TimeUnits.HOUR),
                        "https://anyurl.com", "image", true).formatMessage())
    }

    @Test
    fun test_transliteration() {
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр", "_"))
    }

    @Test
    fun test_humanizeDiff() {
        assertEquals("2 часа назад", Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 7 дней", Date().add(7, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-400, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
    }

    @Test
    fun test_builder() {
        val user = User.Builder().id("1")
            .firstName("Михаил")
            .lastName("Пыжкин")
            .avatar("avatar")
            .rating(1)
            .respect(1)
            .lastVisit(Date().add(-2, TimeUnits.HOUR))
            .isOnline(false)
            .build()
        assertEquals("Михаил", user.firstName)
        assertEquals("Пыжкин", user.lastName)

    }

    @Test
    fun test_plural() {
        assertEquals("1 секунда", TimeUnits.SECOND.plural(1))
        assertEquals("2 секунды", TimeUnits.SECOND.plural(2))
        assertEquals("5 секунд", TimeUnits.SECOND.plural(5))
        assertEquals("1 минута", TimeUnits.MINUTE.plural(1))
        assertEquals("2 минуты", TimeUnits.MINUTE.plural(2))
        assertEquals("5 минут", TimeUnits.MINUTE.plural(5))
        assertEquals("1 час", TimeUnits.HOUR.plural(1))
        assertEquals("2 часа", TimeUnits.HOUR.plural(2))
        assertEquals("19 часов", TimeUnits.HOUR.plural(19))
        assertEquals("1 день", TimeUnits.DAY.plural(1))
        assertEquals("5 дней", TimeUnits.DAY.plural(5))
        assertEquals("222 дня", TimeUnits.DAY.plural(222))
    }

    @Test
    fun test_stripHtml() {
        assertEquals("Образовательное IT-сообщество Skill Branch",
                "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        assertEquals("Образовательное IT-сообщество Skill Branch",
                "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())

    }
}
