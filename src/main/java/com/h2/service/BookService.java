package com.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2.DTO.BookDTO;
import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.Exception.BadRequestException;
import com.h2.Exception.NotFoundException;
import com.h2.entity.Author;
import com.h2.entity.Book;
import com.h2.repository.AuthorRepository;
import com.h2.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Book addBook(BookDTO bookDTO) {
        if (bookDTO.getTitle() == null || bookDTO.getTitle().isEmpty()) {
            throw new BadRequestException("Title cannot be null or empty");
        }
        if (bookDTO.getDescription() == null || bookDTO.getDescription().isEmpty()) {
            throw new BadRequestException("Description cannot be null or empty");
        }
        if (bookDTO.getIsbn() == null || bookDTO.getIsbn().isEmpty()) {
            throw new BadRequestException("ISBN cannot be null or empty");
        }
        if (bookDTO.getAuthors() == null || bookDTO.getAuthors().isEmpty()) {
            throw new BadRequestException("Authors cannot be null or empty");
        }

        if (bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent()) {
            throw new BadRequestException("A book with this ISBN already exists");
        }

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setRating(bookDTO.getRating());
        book.setDescription(bookDTO.getDescription());
        book.setLanguage(bookDTO.getLanguage());
        book.setIsbn(bookDTO.getIsbn());
        book.setBookFormat(bookDTO.getBookFormat());
        book.setEdition(bookDTO.getEdition());
        book.setPages(bookDTO.getPages());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setFirstPublishDate(bookDTO.getFirstPublishDate());
        book.setLikedPercent(bookDTO.getLikedPercent());
        book.setPrice(bookDTO.getPrice());

        List<Author> authors = new ArrayList<>();
        for (String authorName : bookDTO.getAuthors()) {
            Author author = authorRepository.findByName(authorName).orElseGet(() -> {
                Author newAuthor = new Author();
                newAuthor.setName(authorName);
                return authorRepository.save(newAuthor);
            });
            authors.add(author);
        }
        book.setAuthors(authors);

        return bookRepository.save(book);
    }
    

    public List<BookWithAuthorsDTO> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new BadRequestException("Search term cannot be null or empty");
        }
        return bookRepository.searchBooks(searchTerm);
    }

    public Book updateBook(BookDTO bookDTO, String isbn) {
        // Find the book by ISBN
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> 
            new NotFoundException("Book with ISBN " + isbn + " not found"));

        if (isValid(bookDTO.getTitle())) book.setTitle(bookDTO.getTitle());
        if (isValid(bookDTO.getDescription())) book.setDescription(bookDTO.getDescription());
        if (bookDTO.getRating() != null) book.setRating(bookDTO.getRating());
        if (isValid(bookDTO.getLanguage())) book.setLanguage(bookDTO.getLanguage());
        if (isValid(bookDTO.getBookFormat())) book.setBookFormat(bookDTO.getBookFormat());
        if (isValid(bookDTO.getEdition())) book.setEdition(bookDTO.getEdition());
        if (bookDTO.getPages() != null && bookDTO.getPages() > 0) book.setPages(bookDTO.getPages());
        if (isValid(bookDTO.getPublisher())) book.setPublisher(bookDTO.getPublisher());
        if (bookDTO.getPublishDate() != null) book.setPublishDate(bookDTO.getPublishDate());
        if (bookDTO.getFirstPublishDate() != null) book.setFirstPublishDate(bookDTO.getFirstPublishDate());
        if (bookDTO.getLikedPercent() != null) book.setLikedPercent(bookDTO.getLikedPercent());
        if (bookDTO.getPrice() != null) book.setPrice(bookDTO.getPrice());

        // ✅ Optimized Author Handling
        if (bookDTO.getAuthors() != null && !bookDTO.getAuthors().isEmpty()) {
            List<Author> authors = authorRepository.findByNameIn(bookDTO.getAuthors());
            
            // Create new authors if they don't exist
            Set<String> existingAuthorNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toSet());

            List<Author> newAuthors = bookDTO.getAuthors().stream()
                .filter(name -> !existingAuthorNames.contains(name))
                .map(name -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(name);
                    return newAuthor;
                })
                .collect(Collectors.toList());

            if (!newAuthors.isEmpty()) {
                authors.addAll(authorRepository.saveAll(newAuthors));
            }

            book.setAuthors(authors);
        }

        return bookRepository.save(book);
    }

    private boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
