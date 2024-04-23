package org.example.thisandthat.inventory.fixture

import org.example.thisandthat.inventory.InventoryItem

inline fun inventoryItem(block: InventoryItemFixtureBuilder.() -> Unit = {}) =
    InventoryItemFixtureBuilder().apply(block).build()

class InventoryItemFixtureBuilder {
    private val name = faker.name.name()
    private val description = faker.string.letterify("description")
    private val price = faker.random.nextInt(1000, 1000000)
    private val quantity = faker.random.nextInt(0, 10000)
    private val enable = true

    fun build() = InventoryItem(
        name = name,
        description = description,
        price = price,
        quantity = quantity,
        enable = enable
    )

}