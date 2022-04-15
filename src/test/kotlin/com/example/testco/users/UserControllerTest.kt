package com.example.testco.users

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/*

need this to read the application-* yml file.
need this to instantiate repositories below. cannot use with springboottest
use it just for unit test isolation maybe
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)



need this for beforeall usage
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
*/

@ActiveProfiles("test-flyway")
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
internal class UserControllerTest {

    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userRepository: UserRepository
    private var user = User()

    @BeforeEach
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

    @AfterEach
    fun tear() {
        userRepository.deleteAll()
    }

    @Test
    fun shouldReturnCorrectCount() {
        val findAll = userRepository.findAll()
        Assertions.assertEquals(3, findAll.count())
    }

    @Test
    fun createNewUser_shouldAddToUserCount() {
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