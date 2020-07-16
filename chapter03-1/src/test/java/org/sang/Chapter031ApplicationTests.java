package org.sang;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.sang.bean.Book;
import org.sang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * MockMvc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter031ApplicationTests {

    /**
     * Service 测试
     */
    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads() {
        String hello = helloService.sayHello("SpringBoot");
        Assert.assertThat(hello, Matchers.is("Hello SpringBoot !"));
        TestCase.assertEquals(hello, Matchers.is("Hello SpringBoot !"));
    }


    /**
     * Controller 测试
     */

    // 注入 WebApplicationContext 用来模拟 ServletContext 环境
    @Autowired
    WebApplicationContext webApplicationContext;

    // 声明 MockMVC 对象
    MockMvc mockMvc;

    // 每次测试方法执行之前执行该方法
    @Before
    public void before() {
        // 对 MockMvc 对象进行初始化操作
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    /**
     * Get 请求测试
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        // 调用 mockMvc 中的 perform 方法开启一个 RequestBuilder 请求
        MvcResult mvcResult = mockMvc.perform(
                // 通过 MockMvcRequestBuilders 构建具体请求
                MockMvcRequestBuilders
                        // 发起一个 GET 请求
                        .get("/hello")
                        // 请求的内容类型 GET 请求一般为 APPLICATION_FORM_URLENCODED
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        // 请求参数
                        .param("name", "SpringBoot"))
                // 添加返回值得验证规则，利用 MockMvcResultMatchers 进行验证，这里验证响应码是否为 200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 将请求详细信息打印到控制台
                .andDo(MockMvcResultHandlers.print())
                // 返回相应的 MvcResult
                .andReturn();
        // 获取 MvcResult 的返回信息并向其输出
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * Post 请求测试
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = new Book();
        book.setId(1);
        book.setAuthor("罗贯中");
        book.setName("三国演义");
        // 将 Book 对象转为一段 JSON
        String s = objectMapper.writeValueAsString(book);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                // 发起一个 POST 请求
                .post("/book")
                // 请求的内容类型 JSON 数据需要设置为 APPLICATION_JSON
                .contentType(MediaType.APPLICATION_JSON)
                // 上传 JSON 数据
                .content(s))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
