package com.ddubson.repositories;

import com.ddubson.models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorHQLRepository extends CrudRepository<Author, Long> {
}
