package com.h2.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h2.entity.Book;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void testSearchBooksWhenTermIsEmpty() {
        String searchTerm = "";
        assertThrows(IllegalArgumentException.class, () -> bookService.searchBooks(searchTerm));

    }

    @Test
    void testSearchBooksWhenTermIsNull() {
        String searchTerm = null;
        assertThrows(IllegalArgumentException.class, () -> bookService.searchBooks(searchTerm));
    }

    @Test
    void testSearchBooksWhenTermIsValid() {
        String searchTerm = "algorithm";
        List<Book> books = bookService.searchBooks(searchTerm);
        assertTrue(books.size() > 0);
    } 

    @Test
    void testAddBookWhenTitleIsEmpty() {
        String title = "";
        String author = "John Doe";
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(title, author));
    }

    @Test
    void testAddBookWhenAuthorIsEmpty() {
        String title = "Spring Boot";
        String author = "";
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(title, author));
    }

    @Test   
    void testAddBookWhenTitleAndAuthorAreValid() {
        String title = "Spring Boot";
        String author = "John Doe";
        Book book = bookService.addBook(title, author);
        assertTrue(book.getBookId() > 0);
    }
}
