package com.h2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.DTO.BookDTO;
import com.h2.entity.Book;
import com.h2.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> searchBooks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }
        return bookRepository.searchBooks(searchTerm);
    }

    public Book addBook(BookDTO bookDTO) {
        if(bookDTO.getTitle() == null || bookDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if(bookDTO.getDescription() == null || bookDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(bookDTO.getIsbn() == null || bookDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        // Convert BookDTO to Book
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());

        // Optional fields
        book.setRating(bookDTO.getRating());
        book.setAuthor(bookDTO.getAuthor());
        book.setPages(bookDTO.getPages());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());
        book.setLanguage(bookDTO.getLanguage());
        book.setEdition(bookDTO.getEdition());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setFirstPublishDate(bookDTO.getFirstPublishDate());

        return bookRepository.save(book);

    }
}
