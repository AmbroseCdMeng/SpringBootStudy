package org.sang.controller;

import org.sang.bean.Book;
import org.sang.bean.Books;
import org.sang.service.BooksService;
import org.sang.service.BooksService1;
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

    /* SpringBoot 整合 MyBatis */
    @Autowired
    BooksService1 booksService1;

    @GetMapping("/bookOps1")
    public void bookOps1(){
        Books b1 = new Books();
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int i = booksService1.addBook(b1);
        System.out.println("addBook >>> " + i);

        Books b2 = new Books();
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int j = booksService1.updateBookById(b2);
        System.out.println("updateBook >>> " + j);

        Books b3 = booksService1.getBookById(1);
        System.out.println("getBookById >>> " + b3.getName());

        int k = booksService1.deleteBookById(1);
        System.out.println("deleteBookById >>> " + k);

        List<Books> allBooks = booksService1.getAllBooks();
        System.out.println("getAllBooks >>> " + allBooks);
    }
}
