package com.ua.codespace.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @RequestMapping
    public String message() {
        return "message";
    }
}
