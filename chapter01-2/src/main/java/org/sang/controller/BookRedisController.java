package org.sang.controller;

import org.sang.bean.Book;
import org.sang.bean.BookRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 整合 Redis 的控制器
 */

@RestController
public class BookRedisController {

    // RedisTemplate 可以用来操作对象，采用的序列化方案是 JdkSerializationRedisSerializer
    @Autowired
    RedisTemplate redisTemplate;

    // StringRedisTemplate 是 RedisTemplate 的一个 key 和 value 的子类，采用的序列化方案是 StringRedisSerializer
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * StringRedisTemplate 和 RedisTemplate 都是通过 opsForValue、 opsForZSet 或者 opsForSet等方案
     * 首先获取一个操作对象，再使用该操作对象完成数据的读写
     */

    @GetMapping("/test1")
    public void test1(){
        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();
        //向 Redis 中存储一条记录
        ops1.set("name", "木屋烧烤");
        //从 Redis 中取出一条记录
        String  name = ops1.get("name");
        System.out.println(name);

        ValueOperations ops2 = redisTemplate.opsForValue();
        BookRedis book = new BookRedis();
        book.setId(10);
        book.setName("牛排是怎样炼成的？");
        book.setAuthor("老板");
        //向 Redis 中存储一个对象
        ops2.set("book", book);
        //从 Redis 中取出一个对象
        BookRedis bookRedis = (BookRedis)ops2.get("book");
        System.out.println(bookRedis);
    }
}
