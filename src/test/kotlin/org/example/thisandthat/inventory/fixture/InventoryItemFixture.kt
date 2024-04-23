package org.example.thisandthat.inventory.fixture

import com.appmattus.kotlinfixture.kotlinFixture
import org.example.thisandthat.inventory.InventoryItem

val fixture = kotlinFixture()
val inventoryFixture = fixture<InventoryItem> {
    property(InventoryItem::name) { faker.name.name() }
    property(InventoryItem::description) { faker.string.letterify("Description") }
    property(InventoryItem::price) { faker.random.nextInt(0, 10000000) }
    property(InventoryItem::quantity) { faker.random.nextInt(0, 10000000) }
    property(InventoryItem::enable) { true }

}