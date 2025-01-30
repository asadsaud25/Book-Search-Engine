package com.h2.Controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.h2.DTO.BookWithAuthorsDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testSearchBooks() {
        String searchTerm = "algorithm";
        ResponseEntity<BookWithAuthorsDTO[]> response = restTemplate.getForEntity("/books/search?searchTerm={searchTerm}", BookWithAuthorsDTO[].class, searchTerm);

        BookWithAuthorsDTO[] books = response.getBody();
        assertNotNull(books);
        assertTrue(books.length > 0);

        for(BookWithAuthorsDTO book : books) {
            System.out.println(book);
        }
    }

    @Test
    void testAddBook() {
        ResponseEntity<BookWithAuthorsDTO> response = restTemplate.postForEntity("/books/add", null, BookWithAuthorsDTO.class);
        BookWithAuthorsDTO book = response.getBody();
        assertNotNull(book);
        System.out.println(book);
    }
    @Test
    void testAddBookWithDetails() {
        ResponseEntity<BookWithAuthorsDTO> response = restTemplate.postForEntity("/books/add", null, BookWithAuthorsDTO.class);
        BookWithAuthorsDTO book = response.getBody();
        assertNotNull(book);
        System.out.println(book);
    }
    @Test
    void testAddBookWithDetailsAndAuthors() {
        ResponseEntity<BookWithAuthorsDTO> response = restTemplate.postForEntity("/books/add", null, BookWithAuthorsDTO.class);
        BookWithAuthorsDTO book = response.getBody();
        assertNotNull(book);
        System.out.println(book);
    }
    @Test
    void testUpdateBook() {
        ResponseEntity<BookWithAuthorsDTO> response = restTemplate.postForEntity("/books/update", null, BookWithAuthorsDTO.class);
        BookWithAuthorsDTO book = response.getBody();
        assertNotNull(book);
        System.out.println(book);
    }
}
