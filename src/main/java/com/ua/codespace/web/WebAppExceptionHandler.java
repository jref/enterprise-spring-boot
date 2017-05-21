package com.ua.codespace.web;

import com.ua.codespace.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.ua.codespace.web")
public class WebAppExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public String userNotFound(UserNotFoundException e) {
        return e.getMessage();
    }
}
