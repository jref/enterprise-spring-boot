package com.ua.codespace.service.impl;

import com.ua.codespace.mongo.model.Article;
import com.ua.codespace.mongo.repository.ArticleRepository;
import com.ua.codespace.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticlesByAuthor(String author) {
        return articleRepository.findByAuthorName(author);
    }

    @Override
    public Article save(Article article) {
        article.setDate(LocalDate.now());
        return articleRepository.save(article);
    }
}
