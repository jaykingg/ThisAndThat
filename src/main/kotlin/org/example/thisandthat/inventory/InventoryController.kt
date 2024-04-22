package org.example.thisandthat.inventory

import org.example.thisandthat.inventory.payload.SaveItemPayload
import org.example.thisandthat.inventory.payload.UpdateItemPayload
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inventory")
class InventoryController(
    private val inventoryServices: InventoryServices
) {
    @GetMapping("/")
    fun getAllItems(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "name.asc") sort: String
    ): Page<InventoryItem> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sort))
        return inventoryServices.findAllItem(pageable)
    }

    @PostMapping("/")
    fun addItem(@RequestBody payload: SaveItemPayload): InventoryItem {
        return inventoryServices.saveItem(payload)
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): ResponseEntity<InventoryItem> {
        return inventoryServices.getItemById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/")
    fun updateItem(@RequestBody payload: UpdateItemPayload): ResponseEntity<InventoryItem> {
        return inventoryServices.updateItem(payload)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseEntity<Void> {
        inventoryServices.deleteItem(id)
        return ResponseEntity.ok().build()
    }
}