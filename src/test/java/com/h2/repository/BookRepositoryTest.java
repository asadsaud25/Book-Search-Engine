package com.h2.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.h2.DTO.BookWithAuthorsDTO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    /*
     * SET TEST CASE VALUES ACCORDING TO YOUR DATA 
     *                  OR 
     * RUN DBImoporter FILE TO USE FOLLOWING TESTCASES 
     */

    // @Test
    // void testSearBooks() {
    //     List<BookWithAuthorsDTO> books = bookRepository.searchBooks("algorithms");
    //     assertFalse(books.isEmpty());

    //     for(BookWithAuthorsDTO book : books) {
    //         System.out.println(">>> Book Title"+book.getTitle());
    //     }
    // }

    // @Test
    // void testSearchBookWithIsbn() {
    //     BookWithAuthorsDTO book = bookRepository.searchBook("978-9-160-4117-8");
    //     assertNotNull(book);

    //     System.out.println(">>> Book TITLE OF BOOK WITH ISBN 978-9-160-4117-8"+book.getTitle());
    // }

}
