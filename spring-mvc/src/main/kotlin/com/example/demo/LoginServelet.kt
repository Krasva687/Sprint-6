package com.example.demo

import java.time.Instant
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginServlet", urlPatterns = ["/login"])
class LoginServlet: HttpServlet() {
    private val username = "root"
    private val password = "12345"

    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        request!!.getRequestDispatcher("/login.html").forward(request, response)
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (username == request?.getParameter("root") && password == request.getParameter("12345")) {
            val cookie = Cookie("auth", Instant.now().toString())
            response!!.addCookie(cookie)
            response.sendRedirect("/home.html")
        }
        else {
            response!!.sendRedirect("/invalidLogOrPas.html")
        }
    }
}