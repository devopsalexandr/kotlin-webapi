package com.example.restservice.security.jwt

import com.example.restservice.security.exceptions.JwtAuthException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
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
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.KeyPair
import java.util.*
import javax.servlet.http.HttpServletRequest

@PropertySource("classpath:application.properties")
@Component
class JwtTokenProvider {

    @Qualifier("jwtUserDetailsService")
    @Autowired
    lateinit var userDetailsService: UserDetailsService

//    @Value("\${jwt.token.secret}")
//    private var secret: String = "i love you"
    val keyPair: KeyPair = Keys.keyPairFor(SignatureAlgorithm.RS256)


    @Value("\${jwt.token.expiredTime}")
    private lateinit var expiredTime: String

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? = BCryptPasswordEncoder()

    fun createToken(username: String): String
    {
        val claims = Jwts.claims().setSubject(username)
        val now = Date()
        val validate = Date(now.time + expiredTime.toInt())

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validate)
            .signWith(keyPair.private, SignatureAlgorithm.RS256)
            .compact()
    }

    fun getAuthentication(token: String): Authentication?
    {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "")
    }

    fun getUsername(token: String): String
        = Jwts.parserBuilder().setSigningKey(keyPair.public).build().parseClaimsJws(token).body.subject

    fun resolveToken(request: HttpServletRequest): String?
    {
        val token: String? = request.getHeader("Authorization")
        return unwrap(token.toString())
    }

    fun unwrap(token: String): String? =
        if(token.startsWith("Bearer_"))  token.substring(7, token.length) else null


    fun validateToken(token: String): Boolean
    {
        try {

            val claims = Jwts.parserBuilder().setSigningKey(keyPair.public).build().parseClaimsJws(token)
            return !claims.body.expiration.before(Date())

        } catch (e: JwtException){
            throw JwtAuthException("Token is expired and invalid")
        } catch (e: IllegalArgumentException){
            throw JwtAuthException("Token is expired and invalid")
        }

    }

}