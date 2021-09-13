package com.example.restservice.domainModel.services.authService

import com.example.restservice.dal.entities.User
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import com.example.restservice.domainModel.services.authService.Results.RegisterResult

interface AuthService {
    fun registerUser(user: UserDTO): RegisterResult
}