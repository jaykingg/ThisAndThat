package org.example.thisandthat.inventory

import org.example.thisandthat.inventory.payload.SaveItemPayload
import org.example.thisandthat.inventory.payload.UpdateItemPayload
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryServices(
    private val inventoryRepository: InventoryRepository
) {
    fun findAllItem(pageable: Pageable): Page<InventoryItem> {
        return inventoryRepository.findAll(pageable)
    }

    @Transactional
    fun saveItem(payload: SaveItemPayload): InventoryItem {
        return inventoryRepository.save(
            InventoryItem(
                name = payload.name,
                description = payload.description,
                price = payload.price,
                quantity = payload.quantity,
                enable = payload.enable
            )
        )
    }

    fun getItemById(id: Long): InventoryItem {
        return inventoryRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }

    @Transactional
    fun updateItem(payload: UpdateItemPayload): InventoryItem {
        inventoryRepository.findByIdOrNull(payload.id)?.let {
            return inventoryRepository.save(
                it.copy(
                    name = payload.name,
                    description = payload.description,
                    price = payload.price,
                    quantity = payload.quantity,
                    enable = payload.enable
                )
            )
        } ?: throw NotFoundException()
    }

    @Transactional
    fun deleteItem(id: Long) {
        inventoryRepository.findByIdOrNull(id)?.let {
            inventoryRepository.save(
                it.copy(
                    enable = false
                )
            )
        }
    }

}