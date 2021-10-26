package com.example.demo.application.filters

import org.springframework.web.bind.annotation.CookieValue
import java.time.Instant
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletContext
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/app/*", "/api/*"])
private class CookieFilter : HttpFilter() {

    private lateinit var context: ServletContext

    override fun init(filterConfig: FilterConfig) {
        context = filterConfig.servletContext
        context.log("Фильтр аутентификации инициализирован")
    }

    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        val cookies = request!!.cookies

        if (cookies == null) {
            context.log("куки не были найдены")
            context.log("запрос не авторизован")
            response!!.sendRedirect("/login")
        } else {

            val currentTime = Instant.now().toEpochMilli().toString()
            var logMessage = ""

            for (cookie in cookies) {
                if (cookie.name == "auth") {
                    if (currentTime > cookie.value) {
                        logMessage = "Доступ разрешен, куки совпадают"
                        context.log(logMessage)
                        chain!!.doFilter(request, response)
                    } else {
                        logMessage = "Данные пользователя не корректны, попробуйте еще"
                        context.log(logMessage)
                        response!!.sendRedirect("/login")
                    }
                }
            }

            if (logMessage.isEmpty()) {
                context.log("Информация отсутствует")
                response!!.sendRedirect("/login")
            }
        }
    }
}