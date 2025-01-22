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
        String description = "A book on algorithms";
        String isbn = "1234567890";
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(title, description, isbn));
    }

    @Test
    void testAddBookWhenDescriptionIsEmpty() {
        String title = "Algorithms";
        String description = "";
        String isbn = "1234567890";
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(title, description, isbn));
    }
    @Test
    void testAddBookWhenIsbnIsEmpty() {
        String title = "Algorithms";
        String description = "A book on algorithms";
        String isbn = "";
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(title, description, isbn));
    }
    @Test
    void testAddBookWhenAllFieldsAreValid() {
        String title = "Algorithms";
        String description = "A book on algorithms";
        String isbn = "1234567890";
        Book book = bookService.addBook(title, description, isbn);
        assertTrue(book.getBookId() > 0);
    }
}
