package com.example.testco.users

import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<User, Long>
