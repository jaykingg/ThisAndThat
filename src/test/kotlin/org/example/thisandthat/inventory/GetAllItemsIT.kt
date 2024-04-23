package org.example.thisandthat.inventory

import core.PagedView
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.should
import io.kotest.matchers.types.shouldBeTypeOf
import org.example.thisandthat.inventory.fixture.inventoryItem
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class GetAllItemsIT(
    private val inventoryRepository: InventoryRepository,
    private var webTestClient: WebTestClient
) : BehaviorSpec({
    val endPoint = "/api/inventory"
    val request = webTestClient.get().uri(endPoint)



    beforeSpec {
        repeat(100) { index ->
            inventoryRepository.saveAndFlush(
                inventoryItem()
            )
        }
    }

    afterSpec {
        inventoryRepository.deleteAll()
    }


    Given("인증 검증") {
        When("인증이 되지 않았을 때") {
            Then("Response 401 UNAUTHORIZED") {

            }
        }
    }

    Given("RequestParam 검증") {
        When("RequestParam 가 유효하지 않은 경우") {
            Then("Response 400 BAD_REQUEST") {
                webTestClient.get()
                    .uri {
                        it.path(endPoint)
                        it.queryParam("page", -1)
                        it.queryParam("sort", "name,asc")
                            .build()
                    }
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }

    Given("모든 Inventory 가져오기") {
        When("정상 요청") {
            Then("Response 200 Ok") {
                webTestClient.get()
                    .uri {
                        it.path(endPoint)
                            .build()
                    }
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<PagedView<InventoryItem>>()
                    .returnResult()
                    .responseBody!!
                    .should {
                        it.content[0].name.shouldBeTypeOf<String>()
                        it.content[0].description.shouldBeTypeOf<String>()
                        it.content[0].price.shouldBeTypeOf<Int>()
                        it.content[0].price.shouldBeGreaterThanOrEqual(0)
                        it.content[0].quantity.shouldBeTypeOf<Int>()
                        it.content[0].quantity.shouldBeGreaterThanOrEqual(0)
                    }
            }
        }
    }

})