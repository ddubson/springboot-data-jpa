package com.ddubson.repositories;

import com.ddubson.models.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = BEFORE_CLASS)
public class BookHQLRepositoryTest {
    @Autowired
    BookHQLRepository bookRepository;

    @Test
    public void findByTitle_shouldReturnBookWithSpecifiedTitle() {
        String expectedTitle = "War and Peace";
        Book book = bookRepository.findByTitle(expectedTitle);
        assertThat(book.getTitle(), equalTo(expectedTitle));
    }

    @Test
    public void findByAuthorId_returnsAllBooksOfAuthor() {
        List<String> expectedBooks = asList("Anna Karenina", "Crime and Punishment");
        List<Book> books = bookRepository.findByAuthorId(4L);
        assertThat(books.stream().map(Book::getTitle).collect(toList()),
                equalTo(expectedBooks));
    }

    @Test
    public void findByAuthorLastName_returnsAllBooksOfAuthor() {
        List<String> expectedBooks = asList("Anna Karenina", "Crime and Punishment");
        List<Book> books = bookRepository.findByAuthorLastName("Dostoevsky");
        assertThat(books.stream().map(Book::getTitle).collect(toList()),
                equalTo(expectedBooks));
    }
}