package com.h2.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * Entity representing a book in the system.
 * Each book can have multiple authors (many-to-many relationship).
 */
@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id; // Unique identifier for the book

    private String title;               // Title of the book
    private BigDecimal rating;          // Book rating
    private String description;         // Book description
    private String language;            // Language of the book

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;                // ISBN number

    @Column(name = "book_format")
    private String bookFormat;          // Format (e.g., Hardcover, PDF)

    private String edition;             // Edition of the book
    private Integer pages;              // Number of pages
    private String publisher;           // Publisher name

    @Column(name = "publish_date")
    private Date publishDate;           // Date of publication

    @Column(name = "first_publish_date")
    private Date firstPublishDate;      // First publication date

    @Column(name = "liked_percent")
    private BigDecimal likedPercent;    // Percentage of users who liked the book

    private BigDecimal price;           // Price of the book

    @Column(name = "search_vector", columnDefinition = "tsvector")
    @Transient
    private String searchVector;        // Search vector for full-text search (optional, not persisted)

    @ManyToMany
    @JoinTable(
        name = "books_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;       // List of authors for the book
}
