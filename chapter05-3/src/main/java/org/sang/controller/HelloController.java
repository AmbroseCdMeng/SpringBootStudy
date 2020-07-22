package org.sang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin/hello") // admin 角色登录后访问
    public String admin(){
        return "Hello admin !";
    }

    @GetMapping("/user/hello")  // user 角色登录后访问
    public String user(){
        return "Hello user !";
    }

    @GetMapping("/hello")       // 登录后即可访问
    public String hello(){
        return "Hello";
    }
}
