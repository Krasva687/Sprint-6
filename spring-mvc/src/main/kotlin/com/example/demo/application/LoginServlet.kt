package com.example.demo.application

import java.io.IOException
import java.time.Instant
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/login"])
class LoginServlet: HttpServlet() {
    private val username = "root"
    private val password = "12345"

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        request!!.getRequestDispatcher("login.html").forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (username == request?.getParameter("username") && password == request?.getParameter("password")) {
            val cookie = Cookie("auth", Instant.now().toString())
            response!!.addCookie(cookie)
            response.sendRedirect("home.html")
        }
        else {
            response!!.sendRedirect("invalidLogOrPas.html")
        }
    }
}