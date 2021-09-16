package com.example.restservice.security.exceptions

import org.springframework.security.core.AuthenticationException

class JwtAuthException(message: String?) : AuthenticationException(message) {
}