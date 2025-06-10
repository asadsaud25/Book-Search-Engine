package com.h2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.Entity.Author;
import com.h2.Repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findOrCreateAuthor(String name) {

        return authorRepository.findByName(name)
                .orElseGet(() -> {
                    Author newAuthor = new Author(name); // Use the constructor
                    return authorRepository.save(newAuthor);
                });
    }
}
