package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Chapter041Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter041Application.class, args);
    }

}
