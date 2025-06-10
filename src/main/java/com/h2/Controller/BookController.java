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
import com.h2.Entity.Book;
import com.h2.Service.BookService;

/**
 * REST controller for managing books and book search operations.
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * Search for books using a general search term.
     * @param searchTerm The term to search for.
     * @return List of books matching the search term.
     * @throws BadRequestException if the search term is invalid.
     * @throws NotFoundException if no books are found.
     */
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

    /**
     * Search for a book by its ISBN.
     * @param isbn The ISBN of the book.
     * @return Book details with authors.
     */
    @GetMapping("/search/{isbn}")
    public BookWithAuthorsDTO searchBook(@PathVariable String isbn){ 
        BookWithAuthorsDTO book = bookService.searchBook(isbn);
        return book;
    }

    /**
     * Search for books by author name.
     * @param authorName The author's name.
     * @return List of books by the author.
     * @throws BadRequestException if the author name is invalid.
     * @throws NotFoundException if no books are found.
     */
    @GetMapping("/search/author")
    public List<BookWithAuthorsDTO> searchWithAuthor(String authorName) {
        authorName = authorName.trim();
        if (authorName == null || authorName.isEmpty()) {
            throw new BadRequestException("Author name can not be null or empty");
        }
        List<BookWithAuthorsDTO> books = bookService.searchWithAuthor(authorName);
        if (books.isEmpty()) {
            throw new NotFoundException("No books found for the author name: " + authorName);
        }
        return books;
    }

    /**
     * Search for books by title.
     * @param title The book title.
     * @return List of books with the given title.
     * @throws BadRequestException if the title is invalid.
     */
    @GetMapping("/search/title")
    public List<BookWithAuthorsDTO> searchWithTitle(String title) {
        title = title.trim();
        if(title.isEmpty()) {
            throw new BadRequestException("Title can not be null or empty");
        }
        List<BookWithAuthorsDTO> books = bookService.searchBooksWithTitle(title);
        return books;
    }

    /**
     * Add a new book to the database.
     * @param bookDTO The book data.
     * @return The added book.
     */
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.addBook(bookDTO);
        return ResponseEntity.ok(book);
    }

    /**
     * Update an existing book by ISBN.
     * @param bookDTO The updated book data.
     * @param isbn The ISBN of the book to update.
     * @return The updated book.
     * @throws NotFoundException if the book is not found.
     */
    @PutMapping("/update/{isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable String isbn) {
        Book updatedBook = bookService.updateBook(bookDTO, isbn);

        if(updatedBook == null) {
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");
        }
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete a book by ISBN.
     * @param isbn The ISBN of the book to delete.
     * @return Success message.
     * @throws NotFoundException if the book is not found.
     */
    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        boolean isDeleted = bookService.deleteBook(isbn);

        if(!isDeleted) {
            throw new NotFoundException("Book with ISBN: " + isbn + " not found");
        }
        return ResponseEntity.ok("Book with ISBN: " + isbn + " deleted successfully");
    }
}
