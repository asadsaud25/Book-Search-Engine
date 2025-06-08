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

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;
    private BigDecimal rating;
    private String description;
    private String language;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "book_format")
    private String bookFormat;

    private String edition;
    private Integer pages;
    private String publisher;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "first_publish_date")
    private Date firstPublishDate;

    @Column(name = "liked_percent")
    private BigDecimal likedPercent;

    private BigDecimal price;

    @Column(name = "search_vector", columnDefinition = "tsvector")
    @Transient
    private String searchVector; // Optional: Exclude from persistence

    @ManyToMany
    @JoinTable(
        name = "books_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

}
