package org.sang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TestRestTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestRestTemplateTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Test
    public void test3() {
        ResponseEntity<String> hello = restTemplate.getForEntity("/hello?name={0}", String.class, "Michael");
        System.out.println(hello.getBody());
    }
}
