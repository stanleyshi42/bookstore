package com.example.bookstore.controller;

import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.bookstore.entity.Book;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/books/sorted")
    public List<Book> getAllBooksByTitleOrder() {
        return service.getAllBooksByTitleOrder();
    }

    @GetMapping("/books/id/{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id) {
        Book result = service.getBookById(id);
        if (result == null)
            return ResponseEntity.status(404).body("Book not found");
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/books/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return service.getBookByTitle(title);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {

        // Check if book with given title already exists
        Book result = service.getBookByTitle(book.getTitle());
        if (result != null)
            return null;

        return service.addBook(book);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {

        // Check if update target exists
        Book result = service.getBookById(book.getId());
        if (result == null)
            return null;

        return service.updateBook(book);
    }

    @PutMapping("/books/purchase/{id}")
    public boolean purchaseBookById(@PathVariable long id) {

        Book result = service.getBookById(id);

        // Check if book exists and if it's in stock
        if (result == null || result.getQuantity() <= 0)
            return false;

        // Record purchase by decrementing quantity by 1
        result.setQuantity(result.getQuantity() - 1);
        if (service.updateBook(result) != null)
            return true;
        return false;
    }

    @DeleteMapping("/books/id/{id}")
    public boolean deleteBookById(@PathVariable long id) {

        int count = service.getAllBooks().size();
        service.deleteBookById(id);

        // If count changed, then delete was successful
        if (count != service.getAllBooks().size())
            return true;
        return false;
    }

}
