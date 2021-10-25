package com.example.demo.application.controllers.appController

import com.example.demo.application.data.DataBook
import com.example.demo.application.service.BookService
import org.hamcrest.CoreMatchers.containsString
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.*

@SpringBootTest
@AutoConfigureMockMvc
internal class AppControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var bookService: BookService
        
    @BeforeEach
    fun setUp() {
        bookService.addBook(DataBook("Alexander","Alexandrov","alex@gmai.com","89991234567","Москва Таганка"))
        bookService.addBook(DataBook("Ivan","Ivanov","ivan@gmai.com","89991112222","Питер Фонтанка"))
    }

    @Test
    fun getNoteAddFormTest() {
        mockMvc.perform(get("/app/add")
        ).andExpect(status().isOk)
            .andExpect(view().name("addBook"))
    }

    @Test
    fun addNoteTest() {
        mockMvc.perform(post("/app/add")
            .param("firstName", "Alexander")
            .param("lastName", "Alexandrov")
            .param("email", "al@gmail.com")
            .param("phone", "89991234534")
            .param("address", "Таганка Москва")
        ).andExpect(status().isOk)
            .andExpect(view().name("response"))
    }

    @Test
    fun deleteNoteTest() {
        mockMvc.perform(get("/app/0/remove"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("response"))
            .andExpect(content().string(containsString("Контакт удален")))
    }

    @Test
    fun getBookTest() {
        mockMvc.perform(get("/app/1/get"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("contactBook"))
            .andExpect(content().string(containsString("Вывод одного контакта")))
    }

    @Test
    fun getAllContactTest() {
        mockMvc.perform(get("/app/getAll"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("allContact"))
            .andExpect(content().string(containsString("Список адресов")))
    }

    @Test
    fun updateFormContactTest() {
        mockMvc.perform(get("/app/1/update")
        ).andExpect(status().isOk)
            .andExpect(view().name("updateBook"))
    }

    @Test
    fun updateContactTest() {
        mockMvc.perform(post("/app/1/update")
            .param("firstName", "Alexander")
            .param("lastName", "Alexandrov")
            .param("email", "al@gmail.com")
            .param("phone", "89991234534")
            .param("address", "Таганка Москва")
        ).andExpect(status().isOk)
            .andExpect(view().name("response"))
    }
}