package com.example.springmvc.filter

import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletContext
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/**"])
class AuthFilter : HttpFilter() {
    private lateinit var context: ServletContext

    override fun init(filterConfig: FilterConfig) {
        context = filterConfig.servletContext
        context.log("Фильтр логирования инициализирован")
    }

    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        context.log("Метод: ${request!!.method}\n" + "Ресурс: ${request.requestURI}\n")

        val parameters = request.parameterNames

        while (parameters.hasMoreElements()) {
            val parameter = parameters.nextElement()
            context.log("Параметры запроса: $parameter : ${request.getParameter(parameter)}.")
        }
        chain!!.doFilter(request, response)
    }
}