package com.example.restservice.config
import com.example.restservice.contracts.v1.ApiRoutes
import com.example.restservice.security.jwt.JwtConfigurer
import com.example.restservice.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
class SecurityConfig(val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(ApiRoutes.Identity.register).permitAll()
            .antMatchers(ApiRoutes.Identity.login).permitAll()
//            .antMatchers("/api/v1/").hasRole("admin")
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .logout().disable()
            .apply(JwtConfigurer(jwtTokenProvider))
//        super.configure(http)
    }
}