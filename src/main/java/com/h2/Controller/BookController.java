package com.h2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h2.DTO.BookDTO;
import com.h2.DTO.BookWithAuthorsDTO;
import com.h2.Exception.BadRequestException;
import com.h2.Exception.NotFoundException;
import com.h2.entity.Book;
import com.h2.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public List<BookWithAuthorsDTO> searchBooks(@RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new BadRequestException("Search term cannot be null or empty");
        }
        if (searchTerm.length() < 3) {
            throw new BadRequestException("Search term must be at least 3 characters long");
        }
        if (searchTerm.length() > 100) {
            throw new BadRequestException("Search term must be at most 100 characters long");
        }
        if (searchTerm.contains("  ")) {
            throw new BadRequestException("Search term cannot contain consecutive spaces");
        }
        List<BookWithAuthorsDTO> books = bookService.searchBooks(searchTerm);
        if (books.isEmpty()) {
            throw new NotFoundException("No books found for the search term: " + searchTerm);
        }
        return books;
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    // localhost:8080/books/search?searchTerm=algorithm
    // localhost:8080/books/add?title=Java&description=JavaProgramming&isbn=1234567890
    // localhost:8080/books/add?title=Java&description=JavaProgramming&isbn=1234567890&rating=4.5&language=English&bookFormat=PDF&edition=1st&pages=500&publisher=Oracle&publishDate=2021-01-01&firstPublishDate=2020-01-01&author=JamesGosling&likedPercent=90&price=100.00
}
