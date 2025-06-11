package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.enums.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByUserIdAndStatus(long userId, PostStatus status);
    Optional<Page<Post>> findAllByUserId(long userId, Pageable pageable);

    int countByUserId(long userId);
}
