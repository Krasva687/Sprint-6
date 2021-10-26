package com.example.demo.application.controllers.appController

import com.example.demo.application.service.BookService
import com.example.demo.application.data.DataBook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/app")
class AppController {
    @Autowired
    lateinit var bookService: BookService

    @GetMapping("/add")
    fun getNoteAddForm(): String {
        return "addBook"
    }

    @PostMapping("/add")
    fun addNote(@ModelAttribute dataBook: DataBook, model: Model): String {
        bookService.addBook(dataBook)
        model.addAttribute("res", "Контакт добавлен")
        return "response"
    }


    @GetMapping("/{id}/remove")
    fun deleteNote(@PathVariable id: Int, model: Model): String {
        bookService.removeBook(id)
        model.addAttribute("res", "Контакт удален")
        return "response"
    }

    @GetMapping("/{id}/get")
    fun getBook(@PathVariable id: Int, model: Model): String {
        val view = bookService.getBook(id)
        model.addAttribute("res", view)
        return "contactBook"
    }

    @GetMapping("/getAll")
    fun getAllContact(@RequestParam list: Map<String, String>, model: Model): String {
        val searchResult = bookService.getListBooks(list)

        model.addAttribute("res", searchResult)
        model.addAttribute("list", "Вот что нашлось:")
        return "allContact"
    }
    @GetMapping("/{id}/update")
    fun updateFormContact(@PathVariable id: Int): String{
        return "updateBook"
    }

    @PostMapping("/{id}/update")
    fun updateContact(@PathVariable id: Int, @ModelAttribute dataBook: DataBook, model: Model):String {
        bookService.updateBook(dataBook, id)
        model.addAttribute("res","Контакт изменен")
        return "response"
    }
}