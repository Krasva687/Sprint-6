package ru.sber.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import ru.sber.services.FirstService

@Configuration
@ComponentScan("ru.sber.services")
class ServicesConfig {

    fun service(): FirstService {
        return FirstService()
    }


    fun secondService() {
    }
}

@Configuration
@ComponentScan("ru.sber.services")
class AnotherServicesConfig
