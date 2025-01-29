package com.h2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book_with_authors WHERE search_vector @@ to_tsquery(:searchTerm)", nativeQuery = true)
    List<BookWithAuthorsDTO> searchBooks(@Param("searchTerm") String searchTerm);
    
    Optional<Book> findByIsbn(String isbn);
}
