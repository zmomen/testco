package com.example.testco.users

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "email")
    var email: String = "",

    @CreatedDate
    var createdDate: Date = Date()

)