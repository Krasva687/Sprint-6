package ru.sber.services

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Configuration
@Component("singletonService")
@Scope("singleton")
class SingletonService {
    override fun toString(): String {
        return "I am singletonService"
    }
}

@Configuration
@Component("prototypeService")
@Scope("prototype")
class PrototypeService {
    override fun toString(): String {
        return "I am prototypeService"
    }
}
