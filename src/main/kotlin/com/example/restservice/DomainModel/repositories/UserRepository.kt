package com.example.restservice.DomainModel.repositories

import com.example.restservice.dal.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}