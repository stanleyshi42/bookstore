package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookstoreApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext((BeanConfig.class));

    }

}
