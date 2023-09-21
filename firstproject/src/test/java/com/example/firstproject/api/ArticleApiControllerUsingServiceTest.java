package com.example.firstproject.api;

import com.example.firstproject.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// SpringBoot 테스트를 위해 @SpringBootTest 어노테이션을 붙인다.
@SpringBootTest
class ArticleApiControllerUsingServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
    }
}