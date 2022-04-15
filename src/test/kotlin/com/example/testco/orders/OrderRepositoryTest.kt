package com.example.testco.orders

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test-flyway")
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
internal class OrderRepositoryTest {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @BeforeEach
    fun setup() {
        // flyway saves 7 orders in test/resource script. this is the 8th.
        orderRepository.save(Order(8L, 1, 1.0, "desc", Date()))
    }

    @Test
    fun shouldReturnCorrectTotalOrders() {
        val expected = 8L
        assertEquals(expected, orderRepository.count())
    }
}
