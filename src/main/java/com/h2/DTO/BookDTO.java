package com.h2.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


public class BookDTO {
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
    private String author;
    private BigDecimal likedPercent;
    private BigDecimal price;
    private List<String> authors;
    
    // All Getters and Setters
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
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
    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public void setRating(double d) {
        this.rating = BigDecimal.valueOf(d);
    }
    


}
