package com.yaochibao.orderservice.repository

import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.repository.entity.OrderEntity
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
internal class OrderRepositoryTest {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Test
    fun `should return order entity when find order by id given order id`() {
        // given
        val orderId = UUID.fromString("897c37a2-d59e-4e5c-bef6-df3e5ad26425")
        val createDateTime = DateTime("2021-04-17T16:00:00.000")
        val amount = 100.0
        val orderStatus = OrderStatus.WAIT_FOR_PAYMENT

        // when
        val actual = orderRepository.findOrderById(orderId)

        // then
        val expect = OrderEntity(orderId, orderStatus, amount, createDateTime)
        assertEquals(expect, actual)
    }

    @Test
    fun `should return order entity when update order status given order id and new status`() {
        // given
        val amount = 100.0
        val status = OrderStatus.WAIT_FOR_PAYMENT
        val newStatus = OrderStatus.PAID
        val createDateTime = DateTime("2021-04-17T16:00:00.000")
        val originalEntity = orderRepository.save(OrderEntity(
            status = status,
            amount = amount,
            createAt = createDateTime
        ))

        // when
        val newEntity = originalEntity.copy(status = newStatus)
        val actual = orderRepository.save(newEntity)

        // then
        val expect = OrderEntity(
            originalEntity.id,
            newStatus,
            originalEntity.amount,
            originalEntity.createAt
        )
        assertEquals(expect, actual)
    }
}
