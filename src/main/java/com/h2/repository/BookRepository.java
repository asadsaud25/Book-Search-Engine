package com.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.h2.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE search_vector @@ to_tsquery(:searchTerm)", nativeQuery = true)
    List<Book> searchBooks(@Param("searchTerm") String searchTerm);

    @Query(value = "SELECT * FROM books WHERE title = :title", nativeQuery = true)
    Book findByTitle(@Param("title") String title);

    //query to add a book
    @Query(value = "INSERT INTO books (title, author) VALUES (:title, :author) RETURNING *", nativeQuery = true)    
    Book addBook(@Param("title") String title, @Param("author") String author);
    
}

