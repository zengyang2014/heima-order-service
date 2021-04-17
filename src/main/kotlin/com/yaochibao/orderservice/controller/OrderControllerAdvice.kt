package com.yaochibao.orderservice.controller

import com.yaochibao.orderservice.service.exception.AmountAuditError
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(assignableTypes = [OrderController::class])
@Order(1)
class OrderControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [AmountAuditError::class])
    fun handleAmountAuditError(error: AmountAuditError): String {
        return error.message
    }
}
