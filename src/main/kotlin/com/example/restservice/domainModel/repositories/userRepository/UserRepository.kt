package com.example.restservice.domainModel.repositories.userRepository

import com.example.restservice.dal.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findByUsername(string: String): User?

    fun findByEmail(string: String): User?
}