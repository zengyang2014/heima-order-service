package com.yaochibao.orderservice.gateway

import com.yaochibao.orderservice.gateway.vo.TransactionVo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
internal class PaymentClientTest {

    @Autowired
    private lateinit var paymentClient: PaymentClient
    @Test
    fun `should return transaction details when get from payment server given transaction id`() {
        // given
        val id = "1"

        // when
        val actual = paymentClient.getTransaction(id)

        // then
        val expect = TransactionVo("1", 100.0)
        assertEquals(expect, actual)
    }
}
