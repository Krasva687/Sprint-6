package com.example.demo

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@WebServlet("/login")
class MVCAuthPerson: HttpServlet() {

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {

    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {

    }
}