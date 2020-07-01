package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Books;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Repository
@Mapper
public interface BooksMapper {
    int addBook(Books book);
    int deleteBookById(Integer id);
    int updateBookById(Books book);
    Books getBookById(Integer id);
    List<Books> getAllBooks();
}
