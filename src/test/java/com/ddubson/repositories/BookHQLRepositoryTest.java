package com.ddubson.repositories;

import com.ddubson.models.Author;
import com.ddubson.models.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = BEFORE_CLASS)
public class BookHQLRepositoryTest {
    @Autowired
    BookHQLRepository bookRepository;

    @Autowired
    AuthorHQLRepository authorRepository;

    @Before
    public void setUp()  {
        Author author = authorRepository.save(Author.builder().firstName("Leo").lastName("Tolstoy").build());
        bookRepository.save(Book.builder().title("War and Peace").author(author).build());
    }

    @Test
    public void findByTitle_shouldReturnBookWithSpecifiedTitle() {
        String expectedTitle = "War and Peace";
        Book book = bookRepository.findByTitle(expectedTitle);
        assertThat(book.getTitle(), equalTo(expectedTitle));
    }
}