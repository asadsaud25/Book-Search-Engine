package com.h2.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book_with_authors WHERE search_vector @@ to_tsquery(:searchTerm)", nativeQuery = true)
    List<BookWithAuthorsDTO> searchBooks(@Param("searchTerm") String searchTerm);
    
    @Query(value = "SELECT * FROM book_with_authors WHERE isbn @@ to_tsquery(:isbn)", nativeQuery = true)
    BookWithAuthorsDTO searchBook(@Param("isbn") String isbn);

    @Query(value = "SELECT * FROM book_with_authors WHERE authors @@ to_tsquery(:author)", nativeQuery = true)
    List<BookWithAuthorsDTO> searchBooksWithAuthor(@Param("author") String author);

    @Query(value = "SELECT * FROM book_with_authors WHERE title @@ to_tsquery(:title)", nativeQuery = true)
    List<BookWithAuthorsDTO> searchBooksWithTitle(@Param("title") String title);
    
    Optional<Book> findByIsbn(String isbn);
}
