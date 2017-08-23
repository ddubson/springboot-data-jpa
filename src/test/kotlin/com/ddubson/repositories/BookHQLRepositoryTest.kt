package com.ddubson.repositories

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.context.junit4.SpringRunner

@RunWith(value = SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class BookHQLRepositoryTest {
    @Autowired
    lateinit var bookRepository: BookHQLRepository

    @Test
    fun `findByTitle should return book with specified title`() {
        val expectedTitle = "War and Peace"
        val (_, title) = bookRepository.findByTitle(expectedTitle)
        assertThat<String>(title, equalTo<String>(expectedTitle))
    }

    @Test
    fun findByAuthorId_returnsAllBooksOfAuthor() {
        val expectedBooks = listOf("Anna Karenina", "Crime and Punishment")
        val books = bookRepository.findByAuthorId(4L)
        assertThat(books.map { it -> it.title }, equalTo(expectedBooks))
    }

    @Test
    fun findByAuthorLastName_returnsAllBooksOfAuthor() {
        val expectedBooks = listOf("Anna Karenina", "Crime and Punishment")
        val books = bookRepository.findByAuthorLastName("Dostoevsky")
        assertThat(books.map { it -> it.title }, equalTo(expectedBooks))
    }
}