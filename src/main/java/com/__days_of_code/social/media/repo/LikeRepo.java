package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {

    @Query("SELECT COUNT(l) FROM Like l where l.likeableId = :likeableId AND l.likeableType = :likeableType")
    long countByLikeableIdAndLikeableType(@Param("likeableId") long likeableId, @Param("likeableType") String likeableType);

    @Query("SELECT l.user.username FROM Like l where l.likeableId = :likeableId AND l.likeableType = :likeableType")
    List<String> findAllUsernamesByLikeableIdAndLikeableType(@Param("likeableId") long likeableId, @Param("likeableType") String likeableType);

    Optional<Like> findByLikeableIdAndLikeableTypeAndUserId(long likeableId, String likeableType, Long userId);
}
