package com.example.restservice.controllers.v1

import com.example.restservice.contracts.v1.ApiRoutes
import com.example.restservice.contracts.v1.requests.RegisterRequest
import com.example.restservice.contracts.v1.responses.RegisterResponse
import com.example.restservice.domainModel.services.authService.AuthService
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController : ApiController() {

    @Autowired
    private lateinit var authService: AuthService


    @GetMapping(ApiRoutes.Auth.register)
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<RegisterResponse> {

        val userDTO = UserDTO( // ToDo: refactoring with Mapper -> MapStruct
            email = request.email,
            password = request.password,
            username = request.username,
        )

        val result = authService.registerUser(userDTO)

        if(!result.succeeded)
        {
            return ResponseEntity.badRequest().body(RegisterResponse(
                message = result.errors?.get(0)
            ))
        }

        return ResponseEntity.ok(RegisterResponse(result = result.user))
    }
}