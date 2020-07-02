package org.sang.controller;

import org.sang.bean.BookJPA;
import org.sang.service.BookJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.security.PublicKey;
import java.util.List;

/**
 * 整个 Spring Data JPA 的 Controller 层
 */

@RestController
public class BookJPAController {
    @Autowired
    BookJPAService bookService;

    @GetMapping("/jpa_findAll")
    public void findAll(){
        PageRequest pageable = PageRequest.of(2, 3);
        Page<BookJPA> page = bookService.getBookByPage(pageable);

        System.out.println(" 总页数： " + page.getTotalPages());
        System.out.println(" 总记录： " + page.getTotalElements());
        System.out.println(" 查询结果： " + page.getContent());
        System.out.println(" 当前页数： " + page.getNumber() + 1);
        System.out.println(" 当前页记录数： " + page.getNumberOfElements());
        System.out.println(" 每页记录数： " + page.getSize());
    }

    @GetMapping("/jpa_search")
    public void search(){
        List<BookJPA> bs1 = bookService.getBookByIdAndAuthor("吴承恩", 1);
        List<BookJPA> bs2 = bookService.getBooksByAuthorStartingWith("施");
        List<BookJPA> bs3 = bookService.getBooksByPriceGreaterThan(0.1F);
        BookJPA book = bookService.getMaxIdBook();

        System.out.println(bs1);
        System.out.println(bs2);
        System.out.println(bs3);
        System.out.println(book);
    }

    @GetMapping("/jpa_save")
    public void save(){
        BookJPA book = new BookJPA();
        book.setAuthor("卧龙");
        book.setName("卧龙锅巴");
        book.setPrice(3.5F);

        bookService.addBook(book);
    }
}
