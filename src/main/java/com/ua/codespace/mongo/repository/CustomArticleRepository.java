package com.ua.codespace.mongo.repository;


import com.ua.codespace.mongo.model.Article;

import java.util.List;

public interface CustomArticleRepository {
    List<Article> findByAuthorName(String authorName);
}
