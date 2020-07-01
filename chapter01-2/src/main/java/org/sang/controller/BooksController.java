package org.sang.controller;

import org.sang.bean.Book;
import org.sang.bean.Books;
import org.sang.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {

    /* SpringBoot 整合 JdbcTemplate */
    @Autowired
    BooksService booksService;

    @GetMapping("/bookOps")
    public void bookOps() {
        Books b1 = new Books();
        b1.setId(6);
        b1.setName("朝花夕拾");
        b1.setAuthor("鲁迅");
        int i = booksService.addBook(b1);
        System.out.println("add Book " + i + "条 >>> " + b1.getName());

        Books b2 = new Books();
        b2.setId(5);
        b2.setName("SpringBoot 开发实战");
        b2.setAuthor("王松");
        int j = booksService.updateBook(b2);
        System.out.println("update Book" + j + "条 >>> " + b2.getName());

        Books b3 = booksService.getBookById(5);
        System.out.println("getBookById >>> " + b3.getName());

        int k = booksService.deleteBook(5);
        System.out.println("deleteBook" + k + "条 >>> id = " + "5");

        List<Books> allBooks = booksService.getAllBooks();
        System.out.println("getAllBooks >>> " + allBooks);
    }
}
