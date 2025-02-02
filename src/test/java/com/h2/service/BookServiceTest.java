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
import com.h2.Exception.BadRequestException;
import com.h2.Exception.NotFoundException;
import com.h2.entity.Book;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    /*
     * SET TEST CASE VALUES ACCORDING TO YOUR DATA 
     *                  OR 
     * RUN DBImoporter FILE TO USE FOLLOWING TESTCASES 
     */
    
    // Test cases for searchBooks method
    // @Test
    // void testSearchBooksWhenTermIsEmpty() {
    //     String searchTerm = "";
    //     assertThrows(BadRequestException.class, () -> bookService.searchBooks(searchTerm));
    // }
    // @Test
    // void testSearchBooksWhenTermIsNull() {
    //     String searchTerm = null;
    //     assertThrows(BadRequestException.class, () -> bookService.searchBooks(searchTerm));
    // }
    // @Test
    // void testSearchBooksWhenTermIsValid() {
    //     String searchTerm = "algorithm";
    //     List<BookWithAuthorsDTO> books = bookService.searchBooks(searchTerm);
    //     assertTrue(books.size() > 0);
    // } 

    // // Test cases for addBook method
    // @Test
    // void testAddBookWhenTitleIsNull() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setDescription("Java Programming");
    //     bookDTO.setIsbn("1234567890");
    //     bookDTO.setAuthors(List.of("James Gosling"));
    //     assertThrows(BadRequestException.class, () -> bookService.addBook(bookDTO));
    // }
    // @Test
    // void testAddBookWhenDescriptionIsNull() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("Java");
    //     bookDTO.setIsbn("1234567890");
    //     bookDTO.setAuthors(List.of("James Gosling"));
    //     assertThrows(BadRequestException.class, () -> bookService.addBook(bookDTO));
    // }
    // @Test
    // void testAddBookWhenIsbnIsNull() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("Java");
    //     bookDTO.setDescription("Java Programming");
    //     bookDTO.setAuthors(List.of("James Gosling"));
    //     assertThrows(BadRequestException.class, () -> bookService.addBook(bookDTO));
    // }
    // @Test
    // void testAddBookWhenAuthorsIsNull() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("Java");
    //     bookDTO.setDescription("Java Programming");
    //     bookDTO.setIsbn("1234567890");
    //     assertThrows(BadRequestException.class, () -> bookService.addBook(bookDTO));
    // }
    // @Test
    // void testAddBookWhenAllFieldsAreValid() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("TEST");
    //     bookDTO.setDescription("this is for testing purpose");
    //     bookDTO.setIsbn("852-963-7412");
    //     bookDTO.setAuthors(List.of("ANONYMOUS"));
    //     Book book = bookService.addBook(bookDTO);
    //     assertNotNull(book);
    // }
    // @Test
    // void testAddBookWhenIsbnisAlreadyPresent() {
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("Java");
    //     bookDTO.setDescription("Java Programming");
    //     bookDTO.setIsbn("978-2-283-2554-9");
    //     bookDTO.setAuthors(List.of("James Gosling"));
    //     // bookService.addBook(bookDTO);
    //     assertThrows(BadRequestException.class, () -> bookService.addBook(bookDTO));
    // }
    // @Test
    // void testUpdateBookWhenIsbnIsInvalid(){
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("Java");
    //     bookDTO.setDescription("Java Programming");
    //     bookDTO.setAuthors(List.of("James Gosling"));
    //     assertThrows(NotFoundException.class, () -> bookService.updateBook(bookDTO, "1230000"));
    // }
    // @Test
    // void testUpdateBookWhenAllFieldsAreValid(){
    //     BookDTO bookDTO = new BookDTO();
    //     bookDTO.setTitle("TEST");
    //     bookDTO.setDescription("2nd update test successful");
    //     bookDTO.setAuthors(List.of("ANONYMOUS1", "ANONYMOUS2"));
    //     Book book = bookService.updateBook(bookDTO, "852-963-7412");
    //     assertNotNull(book);
    // }
    // @Test
    // void testDeleteBookWhenIsbnIsInvalid(){
    //     assertThrows(NotFoundException.class, () -> bookService.deleteBook("1230000"));
    // }
    // @Test
    // void testDeleteBookWhenIsbnIsValid(){
    //     boolean isDeleted = bookService.deleteBook("852-963-7412");
    //     assertTrue(isDeleted);
    // }
    
}
