package com.vishal.controller;

import com.vishal.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public User getUser(){
        User user = new User();
        user.setEmail("vishal@gmail.com");
        user.setFullName("Vishal Rao Mandala");
        user.setMobile("+1 0000000000");
        user.setRole("Customer");
        return user;
    }
}
