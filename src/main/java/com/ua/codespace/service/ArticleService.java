package com.ua.codespace.service;


import com.ua.codespace.mongo.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();

    List<Article> getArticlesByAuthor(String author);

    Article save(Article article);
}
