package com.h2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2.DTO.BookDTO;
import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.Exception.BadRequestException;
import com.h2.entity.Author;
import com.h2.entity.Book;
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
}
