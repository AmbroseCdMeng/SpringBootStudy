package org.sang.controller;

import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserService service;

    @GetMapping("/login")
    public String login(String username){
        UserDetails userDetails = service.loadUserByUsername(username);
        return userDetails.getUsername();
    }
}
