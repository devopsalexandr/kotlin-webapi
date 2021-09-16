package com.example.restservice.domainModel.repositories.roleRepository
import com.example.restservice.dal.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Int> {

    fun findByName(string: String): Role
}