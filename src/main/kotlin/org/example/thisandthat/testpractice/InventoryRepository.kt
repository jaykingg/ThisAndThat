package org.example.thisandthat.testpractice

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRepository : JpaRepository<InventoryItem, Long> {

    override fun findAll(pageable: Pageable): Page<InventoryItem>
}