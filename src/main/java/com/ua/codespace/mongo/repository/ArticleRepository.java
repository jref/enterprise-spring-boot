package com.ua.codespace.mongo.repository;


import com.ua.codespace.mongo.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String>, CustomArticleRepository {
    List<Article> findByAuthor(String author);
}
