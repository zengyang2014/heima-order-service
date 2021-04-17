package com.yaochibao.orderservice.service.exception

class AmountAuditError(
    override val message: String = "Amount audit error"
): RuntimeException()
