package com.example.firstproject.dto;

import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.Comment;

public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;


    public CommentDto() {}
    public CommentDto(Long id, Long articleId, String nickname, String body) {
        this.id = id;
        this.articleId = articleId;
        this.nickname = nickname;
        this.body = body;
    }

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }

    public Long getId() {
        return this.id;
    }
    public Long getArticleId() {
        return this.articleId;
    }
    public String getNickname() {
        return this.nickname;
    }
    public String getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", nickname='" + nickname + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
