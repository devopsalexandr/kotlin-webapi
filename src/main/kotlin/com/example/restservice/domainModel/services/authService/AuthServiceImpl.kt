package com.example.restservice.domainModel.services.authService

import com.example.restservice.dal.entities.User
import com.example.restservice.dal.entities.additions.Status
import com.example.restservice.domainModel.repositories.userRepository.UserRepository
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl : AuthService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun registerUser(user: UserDTO): User {
        //Todo some logic for check if user exist


        val newUser = User(
            username = user.username,
            email = user.email,
            password = user.password,
            created = LocalDateTime.now(),
            updated = LocalDateTime.now(),
            status = Status.ACTIVE
        )
        return userRepository.save(newUser)
    }
}