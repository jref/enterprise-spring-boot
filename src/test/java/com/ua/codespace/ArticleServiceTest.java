package com.ua.codespace;

import com.ua.codespace.mongo.model.Article;
import com.ua.codespace.mongo.repository.ArticleRepository;
import com.ua.codespace.service.ArticleService;
import com.ua.codespace.service.impl.ArticleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ArticleServiceTest {

    ArticleRepository articleRepository;

    ArticleService articleService;

    @Before
    public void setUp() {
        articleRepository = mock(ArticleRepository.class);
        articleService = new ArticleServiceImpl(articleRepository);
    }

    @Test
    public void getArticlesByAuthorPositive() {
        String author = "James Paul";
        Article article1 = new Article(author, "Some article content 1");
        Article article2 = new Article(author, "Some article content 2");

        when(articleRepository.findByAuthorName(author)).thenReturn(Arrays.asList(article1, article2));

        List<Article> articles = articleService.getArticlesByAuthor(author);
        assertEquals(articles.size(), 2);
        assertTrue(articles.contains(article1));
        assertTrue(articles.contains(article2));
    }
}
