package com.yaochibao.orderservice.service

import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.gateway.PaymentClient
import com.yaochibao.orderservice.repository.OrderRepository
import com.yaochibao.orderservice.repository.entity.OrderEntity
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
        var updatedOrder: OrderEntity
        var orderDto: OrderDto

        if (transaction.amount == order.amount) {
            updatedOrder = orderRepository.save(order.copy(status = OrderStatus.PAID))
            orderDto = OrderDto(updatedOrder.id, updatedOrder.status, updatedOrder.createAt, updatedOrder.amount)
        } else {
            orderDto = OrderDto(order.id, order.status, order.createAt, order.amount)
        }

        return orderDto
    }
}
