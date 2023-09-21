package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> comments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> commentList = commentRepository.findByArticleId(articleId);

        // 2. 엔티티 -> DTO 변환
        List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            CommentDto commentDto = CommentDto.createCommentDto(comment);
            commentDtoList.add(commentDto);
        }
        // 3. 결과 반환
        return commentDtoList;
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패(게시글 없음)"));

        Comment comment = Comment.createComment(commentDto, article);

        Comment createdComment = commentRepository.save(comment);

        return CommentDto.createCommentDto(createdComment);
    }

    @Transactional
    public CommentDto update(Long commentId, CommentDto commentDto) {
        // 1. 댓글 조회 예외 발생
        Comment target = commentRepository.findById(commentId).orElseThrow(() ->
                                                        new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        // 2. 댓글 수정
        target.patch(commentDto);

        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);

        // 4. 댓글 엔티티를 Dto로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long commentId) {
        Comment target = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("잘못된 댓글 id"));
        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
