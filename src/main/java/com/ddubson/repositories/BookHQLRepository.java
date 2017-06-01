package com.ddubson.repositories;

import com.ddubson.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHQLRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
}
