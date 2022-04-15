package com.example.testco.users

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PostMapping
    fun create(
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<User> {
        val newUser = User()
        newUser.name = userRequest.name
        newUser.email = userRequest.email

        val response = userRepository.save(userRequest.toUser())
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}

data class UserRequest(
    val name: String,
    val email: String
)

fun UserRequest.toUser(): User {
    return User(name = this.name, email = this.email)
}