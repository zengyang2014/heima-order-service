package com.yaochibao.orderservice.repository

import com.yaochibao.orderservice.repository.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<OrderEntity, UUID> {
    fun findOrderById(id: UUID): OrderEntity
}
