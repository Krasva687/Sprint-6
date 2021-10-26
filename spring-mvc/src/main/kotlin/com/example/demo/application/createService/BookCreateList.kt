package com.example.demo.application.createService

import com.example.demo.application.data.DataBook
import java.util.concurrent.ConcurrentHashMap

class BookCreateList(
    private val bookList: ConcurrentHashMap<Int, DataBook>
){
    fun addBook(dataBook: DataBook){
        bookList.put(bookList.size, dataBook)
    }
    fun updateBook(bookListNewOrSame: DataBook, id: Int): DataBook?{

        if(bookListNewOrSame.firstName.isNotEmpty()){
                bookList[id]!!.firstName = bookListNewOrSame.firstName
        }
        if(bookListNewOrSame.lastName.isNotEmpty()){
            bookList[id]!!.lastName = bookListNewOrSame.lastName
        }
        if(bookListNewOrSame.email.isNotEmpty()){
            bookList[id]!!.email = bookListNewOrSame.email
        }
        if(bookListNewOrSame.phone.isNotEmpty()){
            bookList[id]!!.phone = bookListNewOrSame.phone
        }
        if(bookListNewOrSame.address.isNotEmpty()) {
            bookList[id]!!.address = bookListNewOrSame.address
        }
        return bookList[id]
    }

    fun getListBooks(): ConcurrentHashMap<Int, DataBook>{
        if(bookList.isEmpty()){
            return ConcurrentHashMap()
        }
        return bookList
    }

    fun removeBook(id: Int): DataBook?{
        return bookList.remove(id)
    }

    fun getBook(id: Int): DataBook? {
        return bookList[id]
    }



}
