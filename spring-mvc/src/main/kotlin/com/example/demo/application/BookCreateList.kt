package com.example.demo.application

import java.util.concurrent.ConcurrentHashMap

class BookCreateList(
    private val bookList: ConcurrentHashMap<Int, DataBook>
){
    fun addBook(dataBook: DataBook){
        bookList.put(bookList.size, dataBook)
    }
    fun updateBook(bookListNewOrSame: DataBook, index: Int): DataBook?{

        if(bookListNewOrSame.firstName.isNotEmpty()){
                bookList[index]!!.firstName = bookListNewOrSame.firstName
        }
        if(bookListNewOrSame.lastName.isNotEmpty()){
            bookList[index]!!.lastName = bookListNewOrSame.lastName
        }
        if(bookListNewOrSame.email.isNotEmpty()){
            bookList[index]!!.email = bookListNewOrSame.email
        }
        if(bookListNewOrSame.phone.isNotEmpty()){
            bookList[index]!!.phone = bookListNewOrSame.phone
        }
        if(bookListNewOrSame.address.isNotEmpty()) {
            bookList[index]!!.address = bookListNewOrSame.address
        }
        return bookList[index]
    }

    fun getListBooks(): ConcurrentHashMap<Int, DataBook>{
        if(bookList.isEmpty()){
            return ConcurrentHashMap()
        }
        return bookList
    }

    fun removeBook(index: Int){
        bookList.remove(index)
    }

    fun getBook(index: Int): DataBook? {
        return bookList[index]
    }



}
