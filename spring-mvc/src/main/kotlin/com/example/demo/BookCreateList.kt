package com.example.demo

import java.util.concurrent.ConcurrentHashMap

class BookCreateList(
    private val bookList: ConcurrentHashMap<Int, DataBook>
){

    fun addOrUpdateBook(bookListNewOrSame: DataBook, index: Int = bookList.size): DataBook?{
        if(index == bookList.size){
            bookList.put(bookList.size, bookListNewOrSame)
            return bookList[bookList.size - 1]
        }
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
