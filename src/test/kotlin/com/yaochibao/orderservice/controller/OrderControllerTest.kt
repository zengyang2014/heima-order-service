package com.yaochibao.orderservice.controller

import com.ninjasquad.springmockk.MockkBean
import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.service.dto.OrderDto
import com.yaochibao.orderservice.service.OrderService
import com.yaochibao.orderservice.service.exception.AmountAuditError
import io.mockk.every
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(OrderController::class)
internal class OrderControllerTest{
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var orderService: OrderService

    @Test
    fun `should return update order when request payment given transaction id and order id`() {
        // given
        val orderId = UUID.randomUUID()
        val transactionId = "transactionId"
        val orderDto = OrderDto(
            orderId = UUID.randomUUID(),
            status = OrderStatus.PAID,
            createAt = DateTime.now(DateTimeZone.UTC),
            amount = 100.0
        )
        every { orderService.handlePaymentRequest(any(), any()) } returns orderDto

        // when
        val actual = mockMvc.perform(
            post("/orders/$orderId/payment-request")
                .header("Authentication", "token")
                .requestAttr("transactionId", transactionId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        // then
        val expect = """{"orderId":"${orderDto.orderId}","status":"${orderDto.status}","createAt":"${orderDto.createAt}","amount":${orderDto.amount}}"""
        assertEquals(expect, actual)
    }

    @Test
    fun `should return error message when order amount not equal paid money`() {
        // given
        val orderId = UUID.randomUUID()
        val transactionId = "transactionId"

        every { orderService.handlePaymentRequest(any(), any()) } throws AmountAuditError()

        // when
        val actual = mockMvc.perform(
            post("/orders/$orderId/payment-request")
                .header("Authentication", "token")
                .requestAttr("transactionId", transactionId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError)
            .andReturn()
            .response
            .contentAsString

        // then
        val expect = "Amount audit error"
        assertEquals(expect, actual)
    }
}
