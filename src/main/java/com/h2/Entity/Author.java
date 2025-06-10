package com.h2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

/**
 * Entity representing an author in the system.
 * Each author can be associated with multiple books (many-to-many relationship).
 */
@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id; // Unique identifier for the author

    private String name; // Name of the author

    @ManyToMany(mappedBy = "authors")
    private List<Book> books; // List of books written by the author

    /**
     * Default constructor.
     */
    public Author() {
    }

    /**
     * Constructor with name.
     * @param name Name of the author
     */
    public Author(String name) {
        this.name = name;
    }
}
