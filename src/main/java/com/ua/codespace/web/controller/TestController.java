package com.ua.codespace.web.controller;

import com.ua.codespace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping
    @ResponseBody
    public String hello() {
        return "Bora-Bora! "+userRepository.findOne(1L).getUsername();
    }

}
