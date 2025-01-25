package com.h2.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h2.DTO.BookDTO;
import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.entity.Book;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    // Test cases for searchBooks method
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
        List<BookWithAuthorsDTO> books = bookService.searchBooks(searchTerm);
        assertTrue(books.size() > 0);
    } 

    // Test cases for addBook method
    
    @Test
    void testAddBookWhenTitleIsEmpty() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("");
        bookDTO.setDescription("A book on algorithms");
        bookDTO.setIsbn("1234567890");
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(bookDTO));
    }

    @Test
    void testAddBookWhenDescriptionIsEmpty() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Algorithms");
        bookDTO.setDescription("");
        bookDTO.setIsbn("1234567890");
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(bookDTO));
    }

    @Test
    void testAddBookWhenIsbnIsEmpty() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Algorithms");
        bookDTO.setDescription("A book on algorithms");
        bookDTO.setIsbn("");
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(bookDTO));
    }

    @Test
    void testAddBookWhenAllFieldsAreValid() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Algorithms");
        bookDTO.setDescription("A book on algorithms");
        bookDTO.setIsbn("1234567890");
        Book book = bookService.addBook(bookDTO);
        assertNotNull(book);
        assertTrue(book.getBookId() > 0);
    }

    
}
