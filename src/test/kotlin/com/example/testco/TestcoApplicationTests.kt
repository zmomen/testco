package com.example.testco

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test-flyway")
class TestcoApplicationTests {

    @Test
    fun contextLoads() {
    }

}
