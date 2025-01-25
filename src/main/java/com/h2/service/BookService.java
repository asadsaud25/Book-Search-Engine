package com.h2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2.DTO.BookDTO;
import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.entity.Author;
import com.h2.entity.Book;
import com.h2.entity.BookAuthor;
import com.h2.repository.AuthorRepository;
import com.h2.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Book addBook(BookDTO bookDTO) {
        // Validate mandatory fields
        if (bookDTO.getTitle() == null || bookDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (bookDTO.getDescription() == null || bookDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (bookDTO.getIsbn() == null || bookDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
    
        // Convert BookDTO to Book
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
    
        // Optional fields
        book.setRating(bookDTO.getRating());
        book.setPages(bookDTO.getPages());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());
        book.setLanguage(bookDTO.getLanguage());
        book.setEdition(bookDTO.getEdition());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setFirstPublishDate(bookDTO.getFirstPublishDate());
    
        // Save the book to generate its ID
        Book savedBook = bookRepository.save(book);
    
        // Process authors
        if (bookDTO.getAuthors() != null && !bookDTO.getAuthors().isEmpty()) {
            List<BookAuthor> bookAuthors = new ArrayList<>();
            for (String authorName : bookDTO.getAuthors()) {
                // Delegate to AuthorService
                Author author = authorRepository.findByName(authorName)
                                .orElseGet(() -> authorRepository.save(new Author(authorName)));

    
                // Create BookAuthor relationship
                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setBook(savedBook);
                bookAuthor.setAuthor(author);
    
                bookAuthors.add(bookAuthor);
            }
            // Set the authors for the book
            savedBook.setAuthors(bookAuthors);
        }
    
        return savedBook;
    }
    


    // public List<Book> searchBooks(String searchTerm) {
    //     if (searchTerm == null || searchTerm.isEmpty()) {
    //         throw new IllegalArgumentException("Search term cannot be null or empty");
    //     }
    //     return bookRepository.searchBooks(searchTerm);
    // }
    public List<BookWithAuthorsDTO> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }
        return bookRepository.searchBooks(searchTerm);
    }
}
