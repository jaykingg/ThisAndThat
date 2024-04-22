package org.example.thisandthat.inventory.payload

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class UpdateItemPayload(
    @field: NotEmpty
    val id: Long,

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