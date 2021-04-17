package com.yaochibao.orderservice.repository.entity

import com.yaochibao.orderservice.constant.OrderStatus
import org.hibernate.annotations.Type
import org.joda.time.DateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
data class OrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,
    val amount: Double,
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    val createAt: DateTime
)
