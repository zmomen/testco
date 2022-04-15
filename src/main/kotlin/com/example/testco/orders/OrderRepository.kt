package com.example.testco.orders

import org.springframework.data.repository.CrudRepository


interface OrderRepository : CrudRepository<Order, Long>
