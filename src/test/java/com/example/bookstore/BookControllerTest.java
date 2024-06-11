package com.example.bookstore;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookControllerTest {

    @Autowired
    BookController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void controllerTest() {

        // Test create
        Book newBook = new Book(0, "Test Book!", "Someone", 5);
        Book result = controller.addBook(newBook);
        assertNotNull(result);

        // Test read
        assertEquals("Test Book!", result.getTitle());

        // Test update
        newBook.setTitle("Updated Book");
        result = controller.updateBook(newBook);
        assertEquals("Updated Book", result.getTitle());

        // Test delete
        // If delete test fails, the test data will remain in DB
        Boolean deleted = controller.deleteBookById(result.getId());
        assertTrue(deleted);
    }

}
