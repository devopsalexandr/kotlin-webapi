package com.example.restservice.controllers.v1

import com.example.restservice.contracts.v1.ApiRoutes
import com.example.restservice.contracts.v1.requests.LoginRequest
import com.example.restservice.contracts.v1.requests.RegisterRequest
import com.example.restservice.contracts.v1.responses.RegisterResponse
import com.example.restservice.domainModel.repositories.userRepository.UserRepository
import com.example.restservice.domainModel.services.authService.AuthService
import com.example.restservice.domainModel.services.authService.Models.UserDTO
import com.example.restservice.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IdentityController : ApiController() {

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @PostMapping(ApiRoutes.Identity.register)
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

    @PostMapping(ApiRoutes.Identity.login)
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any>
    {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))

            val user = userRepository.findByUsername(loginRequest.username)

            if(user === null)
                throw UsernameNotFoundException("User not found")

            val token = jwtTokenProvider.createToken(loginRequest.username)

            return ResponseEntity.ok(token)

        } catch (e: AuthenticationException){
            throw BadCredentialsException("Invalid username or password")
        }
    }

    @GetMapping(ApiRoutes.Identity.profile)
    fun profile(): ResponseEntity<String>
    {
        return ResponseEntity.ok("asdasd")
    }
}