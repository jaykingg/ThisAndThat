package org.example.thisandthat.testpractice.payload

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class SaveItemPayload(
    @field: NotBlank
    val name: String,

    @field: NotBlank
    val description: String,

    @field: Min(0)
    val price: Int,

    @field: Min(0)
    val quantity: Int,

    var enable: Boolean = true
)