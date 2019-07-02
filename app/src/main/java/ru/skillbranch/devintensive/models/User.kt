package ru.skillbranch.devintensive.models

import java.util.*


data class User(
    val id:String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?): this (
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String): this (id, "John", "Doe")

    companion object Factory {
        private var lastId: Int = 0

        fun makeUser(fullName: String?): User {
            lastId++

            val parts: List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0) ?: "John"
            val lastName = parts?.getOrNull(1) ?: "Doe"
            return User(id="$lastId", firstName =firstName, lastName=lastName)
        }
    }
}