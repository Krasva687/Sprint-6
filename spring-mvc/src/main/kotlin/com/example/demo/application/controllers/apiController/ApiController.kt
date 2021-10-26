package com.example.demo.application.controllers.apiController

import com.example.demo.application.data.DataBook
import com.example.demo.application.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@Controller
@RequestMapping("/api")
class ApiController {

    @Autowired
    private lateinit var bookService: BookService

   /* @GetMapping("/add")
    fun getAddNoteForm():ResponseEntity<DataBook>{
        return ResponseEntity(HttpStatus.CREATED)
    }*/

    @PostMapping("/add")
    fun addNote(@RequestBody dataBook: DataBook): ResponseEntity<DataBook> {
        bookService.addBook(dataBook)
        return ResponseEntity(dataBook, HttpStatus.CREATED)
    }

    @GetMapping("/{id}/get")
    fun getBook(@PathVariable id: Int): ResponseEntity<DataBook> {
        return ResponseEntity(bookService.getBook(id), HttpStatus.OK)
    }


    @PostMapping("/getAll")
    fun getAllContact(@RequestBody list: Map<String, String>): ResponseEntity<ConcurrentHashMap<Int, DataBook>> {
        val listResult = bookService.getListBooks(list)
        return ResponseEntity(listResult, HttpStatus.OK)
    }

    @PutMapping("/{id}/update")
    fun updateFormContact(@PathVariable id: Int, @RequestBody dataBook: DataBook): ResponseEntity<DataBook>{
        val newDataBook = bookService.updateBook(dataBook, id)
        return ResponseEntity(newDataBook, HttpStatus.OK)
    }

    @DeleteMapping("/{id}/remove")
    fun deleteNote(@PathVariable id: Int): ResponseEntity<DataBook> {
        return ResponseEntity(bookService.removeBook(id), HttpStatus.OK)
    }
}
