package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.dto.response.CommentResponse;
import com.__days_of_code.social.media.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
}
