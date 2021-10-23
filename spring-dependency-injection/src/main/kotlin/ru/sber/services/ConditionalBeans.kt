package ru.sber.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.*
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component

class ProdProfileCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        /*println(context.environment.activeProfiles.contains("prod"))*/
        return context.environment.activeProfiles.contains("prod")
    }
}

interface ConditionalInterface

@Component
@Conditional(ProdProfileCondition::class)
class ConditionalService : ConditionalInterface {

    override fun toString(): String {
        return "ConditionalService"
    }
}

@Component
class AnotherConditionalService : ConditionalInterface {
    override fun toString(): String {
        return "AnotherConditionalService"
    }
}

@Component
class ConditionalBeanInjectionService {
    @Autowired
    private lateinit var conditionalService: ConditionalInterface


    override fun toString(): String {
        return "ConditionalBeanInjectionService(conditionalService=$conditionalService)"
    }
}