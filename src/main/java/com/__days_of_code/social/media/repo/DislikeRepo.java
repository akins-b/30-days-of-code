package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DislikeRepo extends JpaRepository<Dislike, Long> {
    Optional<Dislike> findByLikeableIdAndLikeableTypeAndUserId(long likeableId, String likeableType, long userId);

    Long countByLikeableIdAndLikeableType(long likeableId, String likeableType);

    List<String> findAllUsernamesByLikeableIdAndLikeableType(long likeableId, String likeableType);
}
