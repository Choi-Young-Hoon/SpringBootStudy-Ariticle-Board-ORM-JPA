package com.example.firstproject.entitiy;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public Comment() {}
    public Comment(Long id, Article article, String nickname, String body) {
        this.id = id;
        this.article = article;
        this.nickname = nickname;
        this.body = body;
    }

    public Long getId() {
        return this.id;
    }
    public Article getArticle() {
        return this.article;
    }
    public String getNickname() {
        return this.nickname;
    }
    public String getBody() {
        return this.body;
    }
}
