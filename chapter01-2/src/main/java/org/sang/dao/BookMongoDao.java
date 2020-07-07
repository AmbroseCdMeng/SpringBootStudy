package org.sang.dao;

import org.sang.bean.Book;
import org.sang.bean.BookMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 整合 MongoDB 的 Dao 层
 */
public interface BookMongoDao extends MongoRepository<BookMongo, Integer> {
    List<BookMongo> findByAuthorContains(String author);
    BookMongo findByNameEquals(String name);
}
