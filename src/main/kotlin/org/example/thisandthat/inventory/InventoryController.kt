package org.example.thisandthat.inventory

import core.PagedView
import core.toPagedView
import jakarta.validation.constraints.Min
import org.example.thisandthat.inventory.payload.SaveItemPayload
import org.example.thisandthat.inventory.payload.UpdateItemPayload
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
    @GetMapping
    fun getAllItems(
        @RequestParam(value = "page", defaultValue = "0") @Min(0) page: Int,
        @RequestParam(value = "size", defaultValue = "10") @Min(1) size: Int,
        @RequestParam(value = "sort", defaultValue = "name") sort: String
    ): PagedView<InventoryItem> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sort))
        return inventoryServices.findAllItem(pageable).toPagedView()
    }

    @PostMapping
    fun addItem(@RequestBody payload: SaveItemPayload): InventoryItem {
        return inventoryServices.saveItem(payload)
    }

    @GetMapping("/{id}")
    fun getItem(@PathVariable id: Long): ResponseEntity<InventoryItem> {
        return inventoryServices.getItemById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping
    fun updateItem(@RequestBody payload: UpdateItemPayload): ResponseEntity<InventoryItem> {
        return inventoryServices.updateItem(payload)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun disableItem(@PathVariable id: Long): ResponseEntity<Void> {
        inventoryServices.disableItem(id)
        return ResponseEntity.ok().build()
    }
}