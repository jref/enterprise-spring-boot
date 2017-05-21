package com.ua.codespace.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class ArticleServiceMonitor {

    @Pointcut("execution(* com.ua.codespace.service.ArticleService.get*(..))")
    public void getSomething() {
    }

    @Before("getSomething()")
    public void printEnterMessage() {
        System.out.println("BEFORE: Getting article!");
    }

    @After("getSomething()")
    public void printReturnMessage() {
        System.out.println("AFTER: Returning article!");
    }

    @Around("getSomething()")
    public List printMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Get article by author: " + joinPoint.getArgs()[0]);
        List articles = (List) joinPoint.proceed();
        int count = articles.size();
        System.out.println("Return " + count + " articles");
        return articles;
    }
}
