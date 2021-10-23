package com.example.demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MVCController {
    @RequestMapping("/")
    fun homePage():String{
        return "Welcome User"
    }
}