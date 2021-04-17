package com.yaochibao.orderservice.service

import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.gateway.PaymentClient
import com.yaochibao.orderservice.repository.OrderRepository
import com.yaochibao.orderservice.repository.entity.OrderEntity
import com.yaochibao.orderservice.service.dto.AmountAuditError
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val paymentClient: PaymentClient
) {
    fun handlePaymentRequest(transactionId: String, orderId: UUID): OrderDto {
        val transaction = paymentClient.getTransaction(transactionId)
        val order = orderRepository.findOrderById(orderId)

        if (transaction.amount == order.amount) {
            val updatedOrder = orderRepository.save(order.copy(status = OrderStatus.PAID))
            return OrderDto(updatedOrder.id, updatedOrder.status, updatedOrder.createAt, updatedOrder.amount)
        }

        throw AmountAuditError()
    }
}
