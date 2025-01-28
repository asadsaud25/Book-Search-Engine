package com.h2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT b.book_id AS bookId, b.title, b.rating, b.description, b.language, b.isbn, b.book_format AS bookFormat, "
            + "b.edition, b.pages, b.publisher, b.publish_date AS publishDate, b.first_publish_date AS firstPublishDate, "
            + "b.liked_percent AS likedPercent, b.price, STRING_AGG(a.name, ', ') AS authors "
            + "FROM books b "
            + "JOIN books_authors ba ON b.book_id = ba.book_id "
            + "JOIN authors a ON ba.author_id = a.author_id "
            + "WHERE b.search_vector @@ to_tsquery(:searchTerm) "
            + "GROUP BY b.book_id", nativeQuery = true)
    List<BookWithAuthorsDTO> searchBooks(@Param("searchTerm") String searchTerm);

    Optional<Book> findByIsbn(String isbn);
}
