package com.example.restservice.domainModel.services.authService.Models

import com.example.restservice.dal.entities.additions.Status
import java.time.LocalDateTime

data class UserDTO (

    val username: String,

    val email: String,

    val password: String,

    var created: LocalDateTime = LocalDateTime.now(),

    val updated: LocalDateTime = LocalDateTime.now(),

    var status: Status = Status.ACTIVE
)