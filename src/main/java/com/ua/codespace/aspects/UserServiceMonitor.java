package com.ua.codespace.aspects;

import com.ua.codespace.model.User;
import com.ua.codespace.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UserServiceMonitor {

    @Before("execution(* com.ua.codespace.service.UserService.get(Long)) && args(id)")
    public void beforeGetUser(Long id) {
        System.out.println("Get user by id: " + id);
    }

    @Pointcut("execution(* com.ua.codespace.service.UserService.get*(..))")
    public void getSomethingFromUserService() {
    }

    @Before("getSomethingFromUserService()")
    public void beforeGet() {
        System.out.println("Get from UserService");
    }

    @After("getSomethingFromUserService() && target(service)")
    public void afterGetUser(JoinPoint joinPoint, UserService service) {
        String methodName = joinPoint.getSignature().getName();
        String className = service.getClass().getName();
        System.out.println("Got information using method " + methodName + " of class " + className);
    }

    @AfterReturning(value = "execution(* com.ua.codespace.service.UserService.getUserFriends(..))", returning = "friends")
    public void afterReturningUsersFriends(List<User> friends) {
        System.out.println("Received " + friends.size() + " friends");
    }

    @Pointcut("execution(* com.ua.codespace.service.UserService.save(..))")
    public void saveUser() {
    }

    @Before("saveUser()")
    public void beforeUserSave() {
        System.out.println("Saving new user");
    }

    @Before("getSomethingFromUserService() || saveUser()")
    public void beforeGetAndSave() {
        System.out.println("I'm here");
    }

}
