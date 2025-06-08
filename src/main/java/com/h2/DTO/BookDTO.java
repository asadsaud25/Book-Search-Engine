package com.h2.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
    
}
