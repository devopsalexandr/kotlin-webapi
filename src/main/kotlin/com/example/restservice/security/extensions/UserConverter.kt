package com.example.restservice.security.extensions

import com.example.restservice.dal.entities.User
import com.example.restservice.dal.entities.additions.Status
import com.example.restservice.security.jwt.JwtUser

fun User.convertToJwt(): JwtUser {
    return JwtUser(
        this.id!!.toInt(),
        this.username,
        this.email,
        this.password,
        this.status
    )
}