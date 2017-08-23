package com.ddubson.repositories

import com.ddubson.models.Book
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookHQLRepository : CrudRepository<Book, Long>{
    @Query("FROM Book b WHERE b.title = :title")
    fun findByTitle(@Param("title") title: String) : Book

    @Query("FROM Book b WHERE b.author.id = :author_id")
    fun findByAuthorId(@Param("author_id") authorId: Long) : List<Book>

    @Query("FROM Book b WHERE b.author.lastName = :author_last_name")
    fun findByAuthorLastName(@Param("author_last_name") authorLastName: String): List<Book>
}