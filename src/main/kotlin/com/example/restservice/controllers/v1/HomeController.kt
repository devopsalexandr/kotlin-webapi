package com.example.restservice.controllers.v1

import com.example.restservice.contracts.v1.ApiRoutes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HomeController : ApiController() {

    @GetMapping(ApiRoutes.Home.index)
    fun index(): String {
        return "sadasdsd"
    }
}