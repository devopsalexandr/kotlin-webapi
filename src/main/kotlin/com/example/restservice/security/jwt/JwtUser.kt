package com.example.restservice.security.jwt

import com.example.restservice.dal.entities.additions.Status
import com.sun.security.auth.UserPrincipal
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser(
    private val id: Int,
    private val userName: String,
    private val email: String,
    private val userPassword: String,
    private val status: Status

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("user")) // roles
    }

    override fun getPassword(): String = userPassword

    override fun getUsername(): String = userName

    fun getEmail(): String = email

    override fun isAccountNonExpired(): Boolean = status === Status.ACTIVE

    override fun isAccountNonLocked(): Boolean = status === Status.ACTIVE

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = status === Status.ACTIVE
}