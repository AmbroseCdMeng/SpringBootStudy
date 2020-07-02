package org.sang.service;

import org.sang.bean.BookJPA;
import org.sang.dao.BookJPADao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  整合 Spring Data JPA 的 Service 层
 */
@Service
public class BookJPAService {
    @Autowired
    BookJPADao bookDao;

    public void addBook(BookJPA book){
        bookDao.save(book);
    }

    public Page<BookJPA> getBookByPage(Pageable pageable){
        return bookDao.findAll(pageable);
    }

    public List<BookJPA> getBooksByAuthorStartingWith(String author){
        return bookDao.getBooksByAuthorStartingWith(author);
    }

    public List<BookJPA> getBooksByPriceGreaterThan(Float price){
        return bookDao.getBooksByPriceGreaterThan(price);
    }

    public BookJPA getMaxIdBook(){
        return bookDao.getMaxIdBook();
    }

    public List<BookJPA> getBookByIdAndAuthor(String author, Integer id){
        return bookDao.getBookByIdAndAuthor(author, id);
    }

    public List<BookJPA> getBooksByIdAndName(String name, Integer id){
        return bookDao.getBooksByIdAndName(name, id);
    }
}
