package com.h2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.addBook(bookDTO);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable String isbn) {
        Book updatedBook = bookService.updateBook(bookDTO, isbn);

        if(updatedBook == null) {
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");
        }
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        boolean isDeleted = bookService.deleteBook(isbn);

        if(!isDeleted) {
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");
        }
        return ResponseEntity.ok("Book with ISBN: " + isbn + " deleted successfully");
    }
}
