package com.h2.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object representing a book along with its authors.
 * Used for transferring book data with author information between layers.
 */
@Getter
@Setter
public class BookWithAuthorsDTO {
    private Long bookId;                // Unique identifier for the book
    private String title;               // Title of the book
    private BigDecimal rating;          // Book rating
    private String description;         // Book description
    private String language;            // Language of the book
    private String isbn;                // ISBN number
    private String bookFormat;          // Format (e.g., Hardcover, PDF)
    private String edition;             // Edition of the book
    private Integer pages;              // Number of pages
    private String publisher;           // Publisher name
    private Date publishDate;           // Date of publication
    private Date firstPublishDate;      // First publication date
    private BigDecimal likedPercent;    // Percentage of users who liked the book
    private BigDecimal price;           // Price of the book
    private String authors;             // Comma-separated author names
    private String searchVector;        // Search vector for full-text search (optional)

    /**
     * Constructor with all fields.
     * @param bookId Book ID
     * @param title Book title
     * @param rating Book rating
     * @param description Book description
     * @param language Book language
     * @param isbn Book ISBN
     * @param bookFormat Book format
     * @param edition Book edition
     * @param pages Number of pages
     * @param publisher Publisher name
     * @param publishDate Publish date
     * @param firstPublishDate First publish date
     * @param likedPercent Liked percent
     * @param price Book price
     * @param authors Comma-separated author names
     * @param searchVector Search vector (optional)
     */
    public BookWithAuthorsDTO(Long bookId, String title, BigDecimal rating, String description, String language, String isbn,
                              String bookFormat, String edition, Integer pages, String publisher, Date publishDate,
                              Date firstPublishDate, BigDecimal likedPercent, BigDecimal price, String authors, String searchVector) {
        this.bookId = bookId;
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.language = language;
        this.isbn = isbn;
        this.bookFormat = bookFormat;
        this.edition = edition;
        this.pages = pages;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.firstPublishDate = firstPublishDate;
        this.likedPercent = likedPercent;
        this.price = price;
        this.authors = authors;
        this.searchVector = searchVector;
    }
}
