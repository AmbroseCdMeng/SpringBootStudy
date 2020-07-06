package org.sang.controller;

import org.sang.bean.Book;
import org.sang.bean.BookMongo;
import org.sang.dao.BookMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.midi.Soundbank;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BookMongoDBController {
    /* MongoRepository */
    @Autowired
    BookMongoDao bookDao;
    @GetMapping("/mongoTest1")
    public void mongoTest1() {
        List<BookMongo> books = new ArrayList<>();
        BookMongo book1 = new BookMongo();
        book1.setId(11);
        book1.setName("Spring+Vue开发实战");
        book1.setAuthor("王松");
        books.add(book1);

        BookMongo book2 = new BookMongo();
        book2.setId(22);
        book1.setName("呐喊");
        book1.setAuthor("鲁迅");

        books.add(book2);

        // MongoRepository 中的 insert 方法插入集合中的数据
        bookDao.insert(books);
        List<BookMongo> books1 = bookDao.findByAuthorContains("王松");
        System.out.println(books1);

        BookMongo book = bookDao.findByNameEquals("呐喊");
        System.out.println(book);


    }

    /* MongoTemplate */
    @Autowired
    MongoTempalte mongoTemplate;
    @GetMapping("/mongoTest2")
    public void mongoTest2 () {
        List<BookMongo> books = new ArrayList<>();
        BookMongo book1 = new BookMongo();
        book1.setId(31);
        book1.setName("围城");
        book1.setAuthor("钱钟书");
        books.add(book1);

        BookMongo book2 = new BookMongo();
        book2.setId(41);
        book2.setName("登鹳雀楼");
        book2.setAuthor("李白");
        books.add(book2);

        mongoTemplate.insertAll(books);
        List<BookMongo> list = mongoTemplate.findAll(BookMongo.class);
        System.out.println(list);
        BookMongo book = mongoTemplate.findById(31, BookMongo.class);
        System.out.println(book);
    }
}
