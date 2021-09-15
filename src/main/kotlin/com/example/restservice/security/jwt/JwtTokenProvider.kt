package com.example.restservice.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest

@Component
@PropertySource("classpath:application.properties")
open class JwtTokenProvider {

    @Qualifier("jwtUserDetailsService")
    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Value("\${jwt.token.secret}")
    private lateinit var secret: String

    @Value("\${jwt.token.expiredTime}")
    private lateinit var expiridTime: String

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? = BCryptPasswordEncoder()

    fun createToken(username: String): String
    {
        val claims = Jwts.claims().setSubject(username)
        val now = Date()
        val validate = Date(now.time + expiridTime.toInt())

        var key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validate)
            .signWith(key)
//            .signWith(SignatureAlgorithm.ES256, secret)
            .compact()
    }

    fun getAuthentication(token: String): Authentication?
    {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "")
    }

    fun getUsername(token: String): String
        = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).body.subject

    fun resolveToken(request: HttpServletRequest): String?
    {
        val token: String? = request.getHeader("Authorization")
        return unwrap(token.toString())
    }

    fun unwrap(token: String): String? =
        if(token.startsWith("Bearer_"))  token.substring(7, token.length) else null


    fun validateToken(token: String)
    {

    }

}