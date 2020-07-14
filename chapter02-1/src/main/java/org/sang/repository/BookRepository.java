package org.sang.repository;

import org.sang.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 *
 */

//默认情况下，请求路径都是实体类名 + s，如果想对请求路径进行重定义，可以通过 `@RepositoryResource` 注解实现。

/**
 * path: 表示所有请求路径中默认为实体类 + s 部分。 http://localhost:8080/bs
 * collectionResourceRel: 表示返回 JSON 集合中实体类集合的 key
 * itemResourceRel: 表示返回 JSON 集合中的单个实体类的 key
 */
//@RepositoryRestResource(path = "bs", collectionResourceRel = "bs", itemResourceRel = "b")

/**
 * 默认情况下，继承了 Repository 或者其子接口，其方法都会暴露出来，如果不想暴露相关操作，可以如下配置
 *
 * 设置 exported 属性的值为 false，则 BookRepository 类中定义的方法包含其继承过来的方法都会失效
 *
 * 如果只是不想暴露某一个方法，也可以直接在具体方法上进行配置 @RestResource(exported=false)
 */
//@RepositoryRestResource(exported = false)

/**
 * SpringBoot 中 CORS 的配置主要由两种方式：全局配置和具体配置。
 *
 * 默认的 RESTful 工程不需要自己提供 `Controller`，所以本应该配置在 `Controller` 上的注解可以直接写在 `BookRepository` 上
 * 此时，BookRepository 中的所有方法都支持跨域，如果只需要某一个方法支持跨域，该注解也可以直接添加到具体方法上
 */
@CrossOrigin
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * 默认的查询方法支持分页查询、排序查询和 id 查询。
     * 如果想要按照某个属性查询，只需要在 `BookRepository` 中定义相关方法并暴露出去即可
     *
     * 自定义查询方法
     *
     *      可以直接通过 http://localhost:8080/books/search 查看该实体类暴露了哪些查询方法
     */

    // 默认查询路径为 http://localhost:8080/books/search/findByAuthorContains?author=鲁迅
    // RestResource 注解可以自定义查询路径 http://localhost:8080/books/search/author?author=鲁迅
    // exported 为 false 时隐藏该方法，不对外暴露
    @RestResource(path = "author", rel = "author", exported=false)
    @CrossOrigin    //支持跨域，该注解可以添加到具体方法上
    List<Book> findByAuthorContains(@Param("author") String author);

    @RestResource(path = "name", rel = "name")
    Book findByNameEquals(@Param("name") String name);

//    @Override
//    @RestResource(exported=false)
//    void deleteById(Integer id);
}
