package com.example.demo.application

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class BookService {
    private val bookList = BookCreateList(ConcurrentHashMap())

    fun addBook(dataBook: DataBook){
        bookList.addBook(dataBook)
    }

    fun updateBook(dataBook: DataBook, index: Int): DataBook? {
        return bookList.updateBook(dataBook, index)
    }
    fun getListBooks(): ConcurrentHashMap<Int, DataBook> =  bookList.getListBooks()

    fun removeBook(index: Int){
        bookList.removeBook(index)
    }

    fun getBook(index: Int): DataBook {
        return bookList.getBook(index)!!
    }

}