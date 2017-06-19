package com.ddubson.repositories;

import com.ddubson.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookHQLRepository extends CrudRepository<Book, Long> {
    @Query("FROM Book b WHERE b.title = :title")
    Book findByTitle(@Param("title") String title);

    @Query("FROM Book b WHERE b.author.id = :author_id")
    List<Book> findByAuthorId(@Param("author_id") Long authorId);

    @Query("FROM Book b WHERE b.author.lastName = :author_last_name")
    List<Book> findByAuthorLastName(@Param("author_last_name") String authorLastName);
}
