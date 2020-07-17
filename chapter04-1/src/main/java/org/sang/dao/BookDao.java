package org.sang.dao;

import org.sang.bean.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
// @CacheConfig 注解指定使用的缓存名称（配置在 ehcache.xml 中）
// 也可以在 @Cacheable 注解中指明缓存名称
@CacheConfig(cacheNames = "book_cache")
public class BookDao {
    // 方法上添加 @Cacheable 注解，表示对该方法进行缓存。
    // 默认情况下，缓存的 key 是方法的参数，缓存的 value 是方法的返回值。
    // 当在其他类中调用该方法时，首先会根据调用参数查看缓存中是否有相关数据，若有，直接使用缓存数据，该方法不会执行。
    // 否则，执行该方法，执行成功后将返回值缓存起来
    // 但是，若在当前类中调用该方法，则缓存不会生效
    // 该注解还有一个 condition 属性用来表明缓存的执行时机，如 @Cacheable(condition= "#id%2==0") 表示 id 为偶数时才进行缓存
    @Cacheable
    public Book getBookById(Integer id) {
        System.out.println("getBookId");
        Book book = new Book();
        book.setId(1);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }

    // 如果不想使用默认的 key，也可以自定义
    // 表示缓存的 key 为参数 book 对象中 id 的值

    // CachePut 一般用于数据更新方法上。其属性与 Cacheable 类似
    // 与 Cacheable 不同的是：
    // 添加 CachePut 注解的方法每次执行时都不去检查缓存中是否有数据，而是直接执行方法，然后将结果缓存，
    // 如果该 key 对应的数据已经被缓存，则会覆盖之前的数据，避免获取到脏数据
    @CachePut(key = "#book.id")
    public Book updateBookById(Book book) {
        System.out.println("updateBookById");
        book.setName("新三国演义");
        return book;
    }

    // 如果不想使用默认的 key，也可以自定义
    // 表示缓存的 key 为参数 id

    // CacheEvict 一般用于删除方法上，表示移除一个 key 对应的缓存。其有两个特殊属性：
    // allEntries：表示是否将所有的缓存数据都移除。默认 false
    // beforeInvocation：表示是否在方法执行之前移除缓存中的数据。默认 false，即在方法执行之后移除缓存中的数据。
    @CacheEvict(key = "#id")
    public void deleteBookById(Integer id){
        System.out.println("deleteBookById");
    }

    // 除了以上两种，Spring 还提供了 root 对象用来生成 key
    // #root.methodName     当前方法名
    // #root.method.name    当前方法对象
    // #root.caches[0].name 当前方法使用的缓存
    // #root.target         当前被调用的对象
    // #root.targetClass    当前被调用的对象的 class
    // #root.args[0]        当前方法参数数组
}
