package com.example.firstproject.entitiy;

import com.example.firstproject.dto.CommentDto;
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

    public static Comment createComment(CommentDto commentDto, Article article) {
        if (commentDto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패 - 댓글 id가 없어야함.z");
        }
        if (commentDto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패 - 게시글의 id가 잘못됨.a");
        }

        return new Comment(
                commentDto.getId(),
                article,
                commentDto.getNickname(),
                commentDto.getBody()
        );
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

    public void patch(CommentDto commentDto) {
        if (commentDto.getNickname() != null) {
            this.nickname = commentDto.getNickname();
        }

        if (commentDto.getBody() != null) {
            this.body = commentDto.getBody();
        }
    }
}
