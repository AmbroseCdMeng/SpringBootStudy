package org.sang;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Json 测试。用来测试 JSON 序列化和反序列化是否正常工作
 */

@RunWith(SpringRunner.class)
// 添加 JsonTest 注解
@JsonTest
class JsonTests {
    // 注入 JacksonTest 进行 JSON 序列化测试
    @Autowired
    JacksonTester<Book> jacksonTester;

    /**
     * 序列化测试
     * @throws IOException
     */
    @Test
    public void testSerialize() throws IOException {
        Book book = new Book();
        book.setId(1);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        // 序列化完成后结果是否与指定json文件内容一致
        Assertions.assertThat(jacksonTester.write(book)).isEqualToJson("book.json");
        // 序列化完成后结果是否含有名为 name 的 key
        Assertions.assertThat(jacksonTester.write(book)).hasJsonPathStringValue("@.name");
        // 序列化完成后结果 key 为 name 对应的值是否为 三国演义
        Assertions.assertThat(jacksonTester.write(book)).extractingJsonPathStringValue("@.name").isEqualTo("三国演义");
    }

    /**
     * 反序列化测试
     * @throws Exception
     */
    @Test
    public  void testDeserialize() throws  Exception{
        String content = "{\"id\":1, \"name\":\"三国演义\", \"author\":\"罗贯中\"}";
        // 反序列化完成后结果对象的 name 值是否为 三国演义
        Assertions.assertThat(jacksonTester.parseObject(content).getName()).isEqualTo("三国演义");
    }
}
