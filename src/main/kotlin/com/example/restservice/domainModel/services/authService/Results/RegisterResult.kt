package com.example.restservice.domainModel.services.authService.Results

import com.example.restservice.dal.entities.User

data class RegisterResult(
    val succeeded: Boolean = true,
    val user: User? = null,
    val errors: List<String>? = null
)