package com.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.entity.Author;
import com.h2.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findOrCreateAuthor(String name) {
        // Use Optional to handle null values
        return authorRepository.findByName(name)
                .orElseGet(() -> {
                    Author newAuthor = new Author(name); // Use the constructor
                    return authorRepository.save(newAuthor);
                });
    }
}
