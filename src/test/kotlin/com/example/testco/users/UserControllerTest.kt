package com.example.testco.users

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userRepository: UserRepository
    private var user = User()

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(UserController(userRepository)).build()
        user.id = 1
        user.name = "zz"
        userRepository.save(user)
        user.id = 2
        userRepository.save(user)
        user.id = 3
        userRepository.save(user)

    }

    @Test
    fun test() {
        val findAll = userRepository.findAll()
        Assertions.assertEquals(3, findAll.count())
    }

    @Test
    fun test2() {
        user.id = 4
        userRepository.save(user)
        val findAll = userRepository.findAll()
        Assertions.assertEquals(4, findAll.count())
    }

    @Test
    fun shouldReturnOk() {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()", `is`(3)))
    }

}