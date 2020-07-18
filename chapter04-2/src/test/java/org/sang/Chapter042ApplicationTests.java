package org.sang;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.sang.bean.Book;
import org.sang.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter042ApplicationTests {

    @Autowired
    BookDao bookDao;

    @Test
    void contextLoads() {
        bookDao.getBookById(1);
        bookDao.getBookById(1);

        bookDao.deleteBookById(1);
        Book book3 = bookDao.getBookById(1);

        System.out.println("book: " + book3);

        Book book = new Book();
        book.setId(1);
        book.setName("新三国演义");
        book.setAuthor("罗贯中");
        bookDao.updateBookById(book);

        Book book4 = bookDao.getBookById(1);
        System.out.println("book4: " + book4);
    }
}
