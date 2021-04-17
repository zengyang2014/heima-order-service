package com.yaochibao.orderservice.gateway

import com.yaochibao.orderservice.gateway.vo.TransactionVo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "payment", url = "\${payment.endpoint}")
interface PaymentClient {
    @GetMapping("/transactions/{transactionId}")
    fun getTransaction(
        @PathVariable transactionId: String
    ): TransactionVo
}
