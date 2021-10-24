package com.example.demo

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import javax.xml.crypto.Data

@Service
class BookService {
    private val bookList = BookCreateList(ConcurrentHashMap())

    fun addOrUpdateBook(dataBook: DataBook){
        bookList.addOrUpdateBook(dataBook)
    }

    fun addOrUpdateBook(dataBook: DataBook, index: Int): DataBook {
        return bookList.addOrUpdateBook(dataBook, index)!!
    }
    fun getListBooks(): ConcurrentHashMap<Int, DataBook>{
        return bookList.getListBooks()
    }

    fun removeBook(index: Int){
        bookList.removeBook(index)
    }

    fun getBook(index: Int): DataBook {
        return bookList.getBook(index)!!
    }

}