package com.example.testco.orders

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*


@Table(name = "orders")
@Entity
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var userId: Long,

    @Column(name = "amount")
    var amount: Double?,

    @Column(name = "description")
    var description: String?,

    @CreatedDate var createdDate: Date = Date()

)