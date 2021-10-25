package com.example.demo.application.service

import com.example.demo.application.data.DataBook
import com.example.demo.application.createService.BookCreateList
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class BookService {
    private val bookList = BookCreateList(ConcurrentHashMap())

    fun addBook(dataBook: DataBook){
        bookList.addBook(dataBook)
    }

    fun updateBook(dataBook: DataBook, id: Int): DataBook? {
        return bookList.updateBook(dataBook, id)
    }
    fun getListBooks(): ConcurrentHashMap<Int, DataBook> =  bookList.getListBooks()

    fun removeBook(id: Int){
        bookList.removeBook(id)
    }

    fun getBook(id: Int): DataBook {
        return bookList.getBook(id)!!
    }

}