package com.example.restservice.controllers.v1

import com.example.restservice.contracts.v1.requests.RegisterRequest
import com.example.restservice.contracts.v1.responses.RegisterResponse
import com.example.restservice.dal.entities.User
import com.example.restservice.domainModel.services.authService.AuthService
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController : ApiController() {

    @Autowired
    private lateinit var authService: AuthService

    fun register(@RequestBody request: RegisterRequest): ResponseEntity<RegisterResponse> {
]
        val user = UserDTO( // ToDo: refactoring with Mapper -> MapStruct
            email = request.email,
            password = request.password,
            username = request.username,
        )

        val result = authService.registerUser(user)

        return ResponseEntity.ok(RegisterResponse))
    }
}