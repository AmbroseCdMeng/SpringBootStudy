package org.sang;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.sang.bean.Book;
import org.sang.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter041ApplicationTests {

    @Autowired
    BookDao bookDao;
    @Test
    void contextLoads() {
        // 执行查询方法
        bookDao.getBookById(1);// 会执行方法
        // 执行查询方法
        bookDao.getBookById(1);// 没有执行方法，说明这里使用了缓存数据

        // 执行删除方法
        bookDao.deleteBookById(1);
        // 执行查询方法
        Book book3 = bookDao.getBookById(1);// 会执行查询方法。因为删除方法中缓存已经被删除了。

        System.out.println("book3: " + book3);

        // 执行更新方法
        Book book = new Book();
        book.setId(1);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        bookDao.updateBookById(book); // 这里不仅会更新数据，也会更新缓存
        // 执行查询方法
        Book book4 = bookDao.getBookById(1);// 没有执行方法，说明这里使用了缓存数据
        System.out.println("book4: " + book4);// 但是输出数据，发现数据已经被更新。所以说明 update 的时候是同步更新了缓存数据的。
    }
}
