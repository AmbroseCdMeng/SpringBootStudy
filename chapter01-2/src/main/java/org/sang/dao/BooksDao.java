package org.sang.dao;

import org.sang.bean.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addBook(Books book){
        String sql = "INSERT INTO book(name, author) VALUES(?,?)";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor());
    }

    public int updateBook(Books book){
        String sql = "UPDATE book SET name=?, author=? WHERE id=?";
        return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getId());
    }

    public int deleteBookById(Integer id){
        String sql = "DELETE FROM book WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    public Books getBookById(Integer id){
        String sql = "SELECT * FROM book WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Books.class), id);
    }

    public List<Books> getAllBooks(){
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Books.class));
    }
}
