package com.h2.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.entity.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSearBooks() {
        List<BookWithAuthorsDTO> books = bookRepository.searchBooks("algorithms");
        assertFalse(books.isEmpty());

        for(BookWithAuthorsDTO book : books) {
            System.out.println(">>> Book Title"+book.getTitle());
        }

    }
}
