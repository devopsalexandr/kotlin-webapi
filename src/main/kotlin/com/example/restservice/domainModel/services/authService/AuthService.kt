package com.example.restservice.domainModel.services.authService

import com.example.restservice.dal.entities.User
import com.example.restservice.domainModel.services.authService.Models.UserDTO

interface AuthService {
    fun registerUser(user: UserDTO): User
}