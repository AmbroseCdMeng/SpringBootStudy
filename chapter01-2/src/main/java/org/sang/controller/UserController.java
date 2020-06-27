package org.sang.controller;

import org.sang.bean.User;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
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

    /* AOP 测试：调用 UserService 的两个方法，即可以看到 LogAspect 中的代码动态嵌入目标方法中的执行 */
    @Autowired
    UserService userService;

    @GetMapping("/getUserById")
    public String getUserById(Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/deleteUserById")
    public void deleteUserBuId(Integer id){
        userService.deleteUserById(id);
    }
}
