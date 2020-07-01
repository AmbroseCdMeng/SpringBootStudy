package org.sang.service;

import org.sang.bean.Book;
import org.sang.bean.Books;
import org.sang.dao.BooksDao;
import org.sang.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 整合 JdbcTemplate
 */
@Service
public class BooksService {

    @Autowired
    BooksDao booksDao;
    public int addBook(Books book){
        return booksDao.addBook(book);
    }

    public int updateBook(Books book){
        return booksDao.updateBook(book);
    }

    public int deleteBook(Integer id){
        return booksDao.deleteBookById(id);
    }

    public Books getBookById(Integer id){
        return booksDao.getBookById(id);
    }

    public List<Books> getAllBooks(){
        return booksDao.getAllBooks();
    }
}
