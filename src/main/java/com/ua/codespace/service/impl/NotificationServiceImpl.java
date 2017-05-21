package com.ua.codespace.service.impl;


import com.ua.codespace.mongo.model.Article;
import com.ua.codespace.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    JmsOperations jmsOperations;

    @Override
    public void sendNewArticleNotification(Article article) {
        jmsOperations.convertAndSend("bamboo.articles", "New article has been posted by " + article.getAuthor());

    }
}
