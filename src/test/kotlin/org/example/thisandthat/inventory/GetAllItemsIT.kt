package org.example.thisandthat.inventory

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetAllItemsIT(
    private val inventoryRepository: InventoryRepository
) : BehaviorSpec({

    Given("인증 검증") {
        When("인증이 되지 않았을 때") {
            Then("") {

            }
        }
    }

})