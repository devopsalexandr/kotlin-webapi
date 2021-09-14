package com.example.restservice.domainModel.services.authService

import com.example.restservice.dal.entities.User
import com.example.restservice.dal.entities.additions.Status
import com.example.restservice.domainModel.repositories.userRepository.UserRepository
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import com.example.restservice.domainModel.services.authService.Results.RegisterResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl : AuthService {

    @Autowired
    private lateinit var userRepository: UserRepository

    private var passwordEncoder = BCryptPasswordEncoder()

    override fun registerUser(user: UserDTO): RegisterResult {

        //Todo some logic for check if user exist by username etc...
        val foundUser = userRepository.findByEmail(user.email)

        if(foundUser !== null)
            return RegisterResult(succeeded = false, errors = arrayListOf("User already exist"))

        val newUser = User(
            username = user.username,
            email = user.email,
            password = passwordEncoder.encode(user.password),
            created = LocalDateTime.now(),
            updated = LocalDateTime.now(),
            status = Status.ACTIVE
        )

         userRepository.save(newUser)

        return RegisterResult(succeeded = true, user = newUser)
    }
}
