package org.sang.controller;

import org.sang.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public User user(){
        User user = new User();
        user.setUsername("Ambrose");
        user.setPassword("123456");
        user.setAddress("广东省深圳市");
        user.setBirthday(new Date());
        return user;
    }
}
