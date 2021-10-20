package ru.sber.services

import org.springframework.beans.factory.BeanNameAware
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import ru.sber.services.processors.MyBeanPostProcessor
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class CallbackBean : InitializingBean, DisposableBean, BeanNameAware {

    var greeting: String? = "What's happening?"

    override fun afterPropertiesSet() {
    }

    @PostConstruct
    override fun destroy() {
        greeting = "Sorry, but I really have to go."
    }

    override fun setBeanName(p0: String) {

    }
}
@Component
class CombinedBean : InitializingBean{
    var postProcessBeforeInitializationOrderMessage: String? = null
    var postConstructOrderMessage: String? = null
    var customInitOrderMessage: String? = null
    var afterPropertiesSetOrderMessage: String? = null
    var postProcessAfterInitializationOrderMessage: String? = null


    override fun afterPropertiesSet() {
        afterPropertiesSetOrderMessage = "afterPropertiesSet() is called"
    }

    fun customInit() {
        customInitOrderMessage = "customInit() is called"
    }
    @PostConstruct
    fun postConstruct() {
        postConstructOrderMessage = "postConstruct() is called"
    }
}

@Component
class BeanFactoryPostProcessorBean : BeanFactoryPostProcessorInterface {

    var preConfiguredProperty: String? = "I'm not set up yet"
    @PostConstruct
    override fun postConstruct() {
        preConfiguredProperty = "Done!"
    }
}

interface BeanFactoryPostProcessorInterface {
    @PostConstruct
    fun postConstruct()
}

