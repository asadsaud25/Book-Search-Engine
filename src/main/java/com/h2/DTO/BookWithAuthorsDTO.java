package com.h2.DTO;

import java.math.BigDecimal;
import java.sql.Date;

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
    private String authors; // This will hold the STRING_AGG result

    // Constructor with 15 parameters
    public BookWithAuthorsDTO(Long bookId, String title, BigDecimal rating, String description, String language, String isbn, String bookFormat, String edition, Integer pages, String publisher, Date publishDate, Date firstPublishDate, BigDecimal likedPercent, BigDecimal price, String authors) {
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
    }

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getFirstPublishDate() {
        return firstPublishDate;
    }

    public void setFirstPublishDate(Date firstPublishDate) {
        this.firstPublishDate = firstPublishDate;
    }

    public BigDecimal getLikedPercent() {
        return likedPercent;
    }

    public void setLikedPercent(BigDecimal likedPercent) {
        this.likedPercent = likedPercent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
