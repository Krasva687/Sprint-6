package ru.sber.services

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Configuration
@Component("firstService")
class FirstService {
    override fun toString(): String {
        return "I am firstService"
    }
}
@Configuration
@Component("secondService")
class SecondService {
    override fun toString(): String {
        return "I am secondService"
    }
}
@Configuration
@Component("thirdService")
class ThirdService {
    override fun toString(): String {
        return "I am thirdService"
    }
}
@Configuration
@Component("fourthService")
class FourthService {
    override fun toString(): String {
        return "I am fourthService"
    }
}
