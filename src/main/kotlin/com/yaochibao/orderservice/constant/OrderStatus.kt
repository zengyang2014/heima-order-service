package com.yaochibao.orderservice.constant

enum class OrderStatus(val description: String) {
    COMPLETED("order completed"),
    CANCELLED("order cancelled"),
    PAID("order paid"),
    WAIT_FOR_PAYMENT("order wait for user to pay"),
    ACCEPTED("order accepted by merchant"),
    READY_FOR_DELIVERY("order ready to delivery to user")
}
