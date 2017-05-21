package com.ua.codespace.mongo.repository.impl;


import com.ua.codespace.mongo.model.Article;
import com.ua.codespace.mongo.repository.CustomArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Query.query;

public class ArticleRepositoryImpl implements CustomArticleRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Article> findByAuthorName(String author) {
        return mongoOperations.find(query(where("author").is(author)), Article.class);
    }
}
