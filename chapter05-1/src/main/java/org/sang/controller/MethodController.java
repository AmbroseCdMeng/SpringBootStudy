package org.sang.controller;

import org.sang.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试注解安全配置
 */

@RestController
public class MethodController {

    @Autowired
    MethodService service;

    @GetMapping("/method/admin")
    public String admin(){
        return service.admin();
    }

    @GetMapping("/method/dba")
    public String dba(){
        return service.dba();
    }

    @GetMapping("/method/user")
    public String user(){
        return service.user();
    }
}
