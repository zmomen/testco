package com.example.testco.users

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<User>> {
        val response = userRepository.findAll()
        return ResponseEntity.ok().body(response.toList())
    }
}