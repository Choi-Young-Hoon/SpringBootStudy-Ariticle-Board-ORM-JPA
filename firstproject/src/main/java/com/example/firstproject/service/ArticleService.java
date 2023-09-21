package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return (List<Article>)articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm form) {
        Article article = form.toEntity();
        Article targetArticle = articleRepository.findById(id).orElse(null);
        if (targetArticle == null) {
            return null;
        }
        targetArticle.patch(article);

        Article updated = articleRepository.save(targetArticle);
        return updated;
    }

    public Article delete(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        articleRepository.delete(article);
        return article;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> forms) {
        // 1. dto(forms) 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = forms.stream()
                                         .map(form -> form.toEntity())
                                         .collect(Collectors.toList());

        // 2. 엔티티 묶음을 db에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생시키기 @Transactional 에 의해 롤백됨
        articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("예외!!"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}
