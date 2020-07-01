package org.sang.service;

import org.sang.bean.Books;
import org.sang.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 整合 MyBatis
 */
@Service
public class BooksService1 {
    @Autowired
    BooksMapper booksMapper;

    public int addBook(Books book){
        return booksMapper.addBook(book);
    }

    public int updateBookById(Books book){
        return booksMapper.updateBookById(book);
    }

    public int deleteBookById(Integer id ){
        return  booksMapper.deleteBookById(id);
    }

    public Books getBookById(Integer id){
        return booksMapper.getBookById(id);
    }

    public List<Books> getAllBooks(){
        return booksMapper.getAllBooks();
    }
}
