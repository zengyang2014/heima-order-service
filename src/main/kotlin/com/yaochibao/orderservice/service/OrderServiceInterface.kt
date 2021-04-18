package com.yaochibao.orderservice.service

import com.yaochibao.orderservice.service.dto.OrderDto
import java.util.*

interface OrderServiceInterface {
    fun handlePaymentRequest(transactionId: String, orderId: UUID): OrderDto
}
