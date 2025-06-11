package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.dto.response.CommentResponse;
import com.__days_of_code.social.media.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostId(Long postId, Pageable pageable);
    Page<Comment> findAllByUserId(Long userId, Pageable pageable);
}
