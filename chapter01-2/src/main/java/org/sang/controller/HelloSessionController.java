package org.sang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Session 共享测试
 */

@RestController
public class HelloSessionController {
    /**
     * 注入项目启动的端口号。区分服务器
     */
    @Value("${server.port}")
    String port;

    /**
     * 向 Session 中存储数据
     * @param name
     * @param session
     * @return
     */
    @PostMapping("/save")
    public String saveName(String name, HttpSession session){
        session.setAttribute("name", name);
        return port;
    }

    /**
     * 从 Session 中获取数据
     * @param session
     * @return
     */
    @GetMapping()
    public String getName(HttpSession session){
        return port + ":" + session.getAttribute("name").toString();
    }
}
