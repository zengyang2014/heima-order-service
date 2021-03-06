package com.yaochibao.orderservice.controller

import com.yaochibao.orderservice.service.dto.OrderDto
import com.yaochibao.orderservice.service.OrderService
import com.yaochibao.orderservice.service.OrderServiceInterface
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderServiceInterface) {

    @PostMapping("/{orderId}/payment-request")
    fun handlePaymentRequest(
        @PathVariable orderId: UUID,
        @RequestAttribute transactionId: String): OrderDto {

        return orderService.handlePaymentRequest(transactionId, orderId)
    }
}
