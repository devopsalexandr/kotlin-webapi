package com.example.restservice.security

import com.example.restservice.domainModel.repositories.userRepository.UserRepository
import com.example.restservice.security.IJwtUserDetailsService
import com.example.restservice.security.extensions.convertToJwt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByUsername(it) }
            ?: throw UsernameNotFoundException("User not found username $username")

        return user.convertToJwt()
    }

//    getUser
}