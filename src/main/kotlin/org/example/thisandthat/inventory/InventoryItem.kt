package org.example.thisandthat.inventory

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class InventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val enable: Boolean = true
)