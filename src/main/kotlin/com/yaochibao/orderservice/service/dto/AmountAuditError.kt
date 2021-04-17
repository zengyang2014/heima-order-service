package com.yaochibao.orderservice.service.dto

class AmountAuditError(
    override val message: String = "Amount audit error"
): RuntimeException()
