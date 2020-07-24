package org.sang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model) {
        // 构造 UsernamePasswordToken 实例
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取一个 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        try {
            // 调用 login 方法执行登录视频
            subject.login(token);
        } catch (AuthenticationException e) {
            // 登录异常返回携带错误信息的登录页面
            model.addAttribute("error", "用户名或密码输入错误！");
            return "login";
        }
        // 登录成功重定向到 index
        return "redirect:/index";
    }

    // 需要 admin 角色访问
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    // 需要 admin 或者 user 角色访问
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    @GetMapping("/user")
    public String user(){
        return "user";
    }
}
