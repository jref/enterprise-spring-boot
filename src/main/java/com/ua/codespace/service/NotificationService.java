package com.ua.codespace.service;

import com.ua.codespace.mongo.model.Article;

public interface NotificationService {
    void sendNewArticleNotification(Article article);

}