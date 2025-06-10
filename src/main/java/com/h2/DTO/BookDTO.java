package com.h2.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for transferring book data between layers.
 * Used for creating or updating book records.
 */
@Getter
@Setter
public class BookDTO {
    private String title;                // Title of the book
    private BigDecimal rating;           // Book rating
    private String description;          // Book description
    private String language;             // Language of the book
    private String isbn;                 // ISBN
    private String bookFormat;           // Format (e.g., Hardcover, PDF)
    private String edition;              // Edition of the book
    private Integer pages;               // Number of pages
    private String publisher;            // Publisher name
    private Date publishDate;            // Date of publication
    private Date firstPublishDate;       // First publication date
    private String author;               // Single author name (optional, for backward compatibility)
    private BigDecimal likedPercent;     // Percentage of users who liked the book
    private BigDecimal price;            // Price of the book
    private List<String> authors;        // List of author names
}
