package com.yaochibao.orderservice.service.dto

import com.yaochibao.orderservice.constant.OrderStatus
import org.joda.time.DateTime
import java.util.*

data class OrderDto(
    val orderId: UUID,
    val status: OrderStatus,
    val createAt: DateTime,
    val amount: Double
)
