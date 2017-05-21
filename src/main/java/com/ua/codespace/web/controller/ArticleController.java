package com.ua.codespace.web.controller;

import com.ua.codespace.mongo.model.Article;
import com.ua.codespace.mongo.repository.ArticleRepository;
import com.ua.codespace.service.ArticleService;
import com.ua.codespace.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.GET)
    String getArticles(@RequestParam(required = false) String author, Model model) {
        List<Article> articles;
        if (author != null && !author.isEmpty()) {
            articles = articleService.getArticlesByAuthor(author);
        } else {
            articles = articleRepository.findAll();
        }
        model.addAttribute("articles", articles);
        return "articles";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getArticleForm() {
        return "articleForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String save(Article article) {
        articleService.save(article);
        notificationService.sendNewArticleNotification(article);
        return "redirect:/articles?author=" + article.getAuthor();
    }
}
