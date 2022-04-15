package com.example.testco.users

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.repository.CrudRepository
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "email")
    var email: String = ""

    @CreatedDate
    var createdDate: Date = Date()

}

interface UserRepository : CrudRepository<User, Long>
