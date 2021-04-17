package com.yaochibao.orderservice.service

import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.gateway.PaymentClient
import com.yaochibao.orderservice.gateway.vo.TransactionVo
import com.yaochibao.orderservice.repository.OrderRepository
import com.yaochibao.orderservice.repository.entity.OrderEntity
import com.yaochibao.orderservice.service.dto.AmountAuditError
import io.mockk.every
import io.mockk.mockk
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OrderServiceTest {

    private val paymentClient = mockk<PaymentClient>()
    private val repository = mockk<OrderRepository>()

    private val orderService = OrderService(repository, paymentClient)

    @Test
    fun `should update the payment status when given transaction exist`() {
        // given
        val transactionId = "2"
        val transaction = TransactionVo(transactionId, 200.0)
        every { paymentClient.getTransaction(any()) } returns transaction

        val order = OrderEntity(
            status = OrderStatus.WAIT_FOR_PAYMENT,
            amount = 200.0,
            createAt = DateTime.now()
        )
        every { repository.findOrderById(any()) } returns order
        every { repository.save(any()) } returns order.copy(status = OrderStatus.PAID)

        // when
        val actual = orderService.handlePaymentRequest(transactionId, order.id)

        // then
        assertEquals(OrderStatus.PAID, actual.status)
    }

    @Test
    fun `should throw error when given transaction amount not equal order amount`() {
        // given
        val transactionId = "2"
        val transaction = TransactionVo(transactionId, 100.0)
        every { paymentClient.getTransaction(any()) } returns transaction

        val order = OrderEntity(
            status = OrderStatus.WAIT_FOR_PAYMENT,
            amount = 200.0,
            createAt = DateTime.now()
        )
        every { repository.findOrderById(any()) } returns order

        // when
        val actual = assertThrows(AmountAuditError::class.java) {
            orderService.handlePaymentRequest(transactionId, order.id)
        }

        // then
        assertEquals("Amount audit error", actual.message)
    }
}
