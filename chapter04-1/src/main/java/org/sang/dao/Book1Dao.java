package org.sang.dao;

import org.sang.bean.Book;
import org.sang.generator.MyKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 使用自定义的 key 生成器
 */

@Service
@CacheConfig(cacheNames = "book_cache")
public class Book1Dao {
    @Autowired
    MyKeyGenerator myKeyGenerator;
    @Cacheable(keyGenerator = "myKeyGenerator")
    public Book getBookById(Integer id){
        System.out.println("getBookById");
        Book book = new Book();
        book.setId(1);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }
}
