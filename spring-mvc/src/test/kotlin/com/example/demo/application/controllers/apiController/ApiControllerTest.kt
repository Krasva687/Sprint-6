package com.example.demo.application.controllers.apiController

import com.example.demo.application.data.DataBook
import com.example.demo.application.service.BookService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.util.concurrent.ConcurrentHashMap
import javax.annotation.sql.DataSourceDefinition
import javax.annotation.sql.DataSourceDefinitions

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ApiControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var bookService: BookService
    private val headers = HttpHeaders()

    @BeforeEach
    fun setUp(){
        headers.add("Cookie", login())
        bookService.addBook(DataBook("Alexander","Alexandrov","alex@gmai.com","89991234567","Москва Таганка"))
        bookService.addBook(DataBook("Ivan","Ivanov","ivan@gmai.com","89991112222","Питер Фонтанка"))
    }

    private fun url(s: String): String {
        return "http://localhost:${port}/${s}"
    }

    private fun login(): String? {
        val request: MultiValueMap<String, String> = LinkedMultiValueMap()
        request.set("username", "root")
        request.set("password", "12345")

        val response = restTemplate.postForEntity(url("login"), HttpEntity(request, HttpHeaders()), String::class.java)

        return response!!.headers["Set-Cookie"]!![0]
    }



    @ParameterizedTest
    @MethodSource("dataBookSpecimen")
    fun addNoteTest(dataBook: DataBook) {
        val response = restTemplate.exchange(
                url("api/add"),
                HttpMethod.POST,
                HttpEntity(dataBook, headers),
                DataBook::class.java
            )

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.body)
        assertEquals(dataBook.firstName, response.body!!.firstName)
        }

    @ParameterizedTest
    @MethodSource("getId")
    fun deleteNote(id: Int) {
        val response = restTemplate.exchange(
            url("api/$id/remove"),
            HttpMethod.DELETE,
            HttpEntity(emptyMap<String, String>(), headers),
            ConcurrentHashMap::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
    }

    @ParameterizedTest
    @MethodSource("getId")
    fun getBook(id: Int) {
        val response = restTemplate.exchange(
            url("api/$id/get"),
            HttpMethod.GET,
            HttpEntity(id, headers),
            DataBook::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
    }

    @Test
    fun getAllContact() {
        val response = restTemplate.exchange(
            url("api/getAll"),
            HttpMethod.POST,
            HttpEntity(emptyMap<String, String>(), headers),
            ConcurrentHashMap::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
    }

    @ParameterizedTest
    @MethodSource("updateIdBook")
    fun updateFormContact(id: Int, dataBook: DataBook) {
        val response = restTemplate.exchange(
            url("api/$id/update"),
            HttpMethod.PUT,
            HttpEntity(dataBook, headers),
            DataBook::class.java
        )

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(dataBook.firstName, response.body!!.firstName)

    }

    companion object {
        @JvmStatic
        fun dataBookSpecimen() = listOf(
            DataBook("Alexander","Alexandrov","alex@gmai.com","89991234567","Москва Таганка"),
            DataBook("Stas","Alexandrov","alesdsdsdx@gmai.com","","Воронеж")
        )

        @JvmStatic
        fun getId() = listOf(
            1, 0
        )

        @JvmStatic
        fun updateIdBook() = listOf(
            Arguments.of(0, DataBook("Alexander","Alexandrov","alex@gmai.com","89991234567","Москва Таганка")),
            Arguments.of(1, DataBook("Stas","Alexandrov","alesdsdsdx@gmai.com","","Воронеж"))
        )
    }
}
