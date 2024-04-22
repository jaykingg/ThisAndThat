package org.example.thisandthat.kotlinpractice.domain

import java.time.LocalDate

data class Person(
    var name: String,
    val birth: LocalDate,
    val gender: Enum<Gender>,
    val height: Int,
    val weight: Int
)