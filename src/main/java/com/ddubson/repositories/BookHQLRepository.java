package com.ddubson.repositories;

import com.ddubson.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHQLRepository extends CrudRepository<Book, Long> {
    @Query("FROM Book b WHERE b.title = :title")
    Book findByTitle(@Param("title") String title);
}
