package org.sang.controller;

import org.sang.Bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    Book book;

    @GetMapping("/book")
    public String book(){
        return book.toString();
    }
}


