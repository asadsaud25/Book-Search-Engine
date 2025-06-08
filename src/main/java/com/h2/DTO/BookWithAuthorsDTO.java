package com.h2.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class BookWithAuthorsDTO {
    private Long bookId;
    private String title;
    private BigDecimal rating;
    private String description;
    private String language;
    private String isbn;
    private String bookFormat;
    private String edition;
    private Integer pages;
    private String publisher;
    private Date publishDate;
    private Date firstPublishDate;
    private BigDecimal likedPercent;
    private BigDecimal price;
    private String authors;
    private String searchVector; 

    // Constructor with all fields
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
