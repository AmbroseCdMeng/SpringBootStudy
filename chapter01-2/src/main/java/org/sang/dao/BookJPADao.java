package org.sang.dao;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Book;
import org.sang.bean.BookJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 整合 JPA 的 BookDao
 *
 * 自定义 Dao 继承 JpaRepository。JpaRepository 中提供了一些基本的数据操作方法，有增删改查、分页查询、排序查询等。
 */
public interface BookJPADao extends JpaRepository<BookJPA, Integer> {

    //Spring Data JPA 支持既定规范命名查询（方法名复合既定规范，自动生成对应 SQL）

    /**
     *     keyWords             示例                                对应 SQL
     *     And                  findByNameAndAge                    where name=? and age=?
     *     Or                   findByNameOrAge                     where name=? or age=?
     *     Is/Equals            findByAgeIs/findByAgeEquals         where age=?
     *     Between              findByAgeBetween                    where age between ? and ?
     *     LessThan/Before      findByAgeLessThan/findByAgeBefore   where age < ?
     *     LessThanEquals       findByAgeLessThanEquals             where age <= ?
     *     GreaterThan/After    findByAgeGreaterThan/findByAgeAfter where age > ?
     *     GreaterThanEquals    findByAgeGreaterThanEquals          where age >= ?
     *     IsNull               findByNameIsNull                    where name is null
     *     IsNotNull/NotNull    findByNameNotNull                   where name is not null
     *     Not                  findByNameNot                       where name <> ?
     *     In                   findByAgeIn                         where age in(?)
     *     NotIn                findByAgeNotIn                      where age not in(?)
     *     NotLike              findByNameNotLike                   where name not like ?
     *     StartingWith         findByNameStartingWith              where name like '?%'
     *     EndingWith           findByNameEndingWith                where name like '%?'
     *     Containing/Contains  findByNameContains                  where name like '%?%'
     *     True                 findByEnabledTrue                   where enabled = true
     *     False                findByEnabledFalse                  where enabled = false
     *     IgnoreCase           findByNameIgnoreCase                where UPPER(name)=UPPER(?)
     *
     *     OrderBy              findByAgeGreaterThanOrderByIdDesc   where age > ? order by id desc
     */

    // 查询作者名以某字符开头的所有书
    List<BookJPA> getBooksByAuthorStartingWith(String author);
    // 查询价格大于某值得所有书
    List<BookJPA> getBooksByPriceGreaterThan(Float price);

    // Spring Data JPA 支持原生 SQL。 nativeQuery = true 表示使用原生 SQL 语句
    @Query(value = "select * from t_book where id=(select max(id) from t_book)", nativeQuery = true)
    BookJPA getMaxIdBook();

    // Spring Data JPA 支持默认的 JPQL。JPQL 是一种可移植的面向对象表达式语言。
    // 通过类名和属性（并非数据库列名）来进行参数绑定。参数需要使用 @Param 绑定。
    @Query("select b from t_book b where b.id > :id and b.author=:author")
    List<BookJPA> getBookByIdAndAuthor(@Param("author") String author, @Param("id") Integer id);

    // Spring Data JPA 支持默认的 JPQL。
    // 通过占位符进行参数传递。参数顺序有严格要求。
    @Query("select b from t_book b where b.id < ?2 and b.name like %?1%")
    List<BookJPA> getBooksByIdAndName(String name, Integer id);

    // 如果方法涉及修改操作，需要添加 @Modifying 注解并添加事务
}
