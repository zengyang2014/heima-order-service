package com.yaochibao.orderservice.functiontest

import com.yaochibao.orderservice.constant.OrderStatus
import com.yaochibao.orderservice.service.dto.OrderDto
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class PaymentRequestTest{
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return update order when request payment given transaction id and order id`() {
        // given
        val orderId = UUID.fromString("897c37a2-d59e-4e5c-bef6-df3e5ad26425")
        val transactionId = "1"

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
        val orderDto = OrderDto(
            orderId = orderId,
            status = OrderStatus.PAID,
            createAt = DateTime("2021-04-17T08:00:00.000Z", DateTimeZone.UTC),
            amount = 100.0
        )
        val expect = """{"orderId":"${orderDto.orderId}","status":"${orderDto.status}","createAt":"${orderDto.createAt}","amount":${orderDto.amount}}"""
        assertEquals(expect, actual)
    }
}
