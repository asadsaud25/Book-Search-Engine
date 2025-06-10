package com.h2.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity representing the relationship between books and authors.
 * Each instance links a single book to a single author (many-to-many mapping table).
 */
@Entity
@Table(name = "books_authors")
@Data
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the book-author relationship

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book; // The book in the relationship

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author; // The author in the relationship
}
