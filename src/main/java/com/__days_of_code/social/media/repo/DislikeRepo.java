package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Dislike;
import com.__days_of_code.social.media.enums.LikeableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DislikeRepo extends JpaRepository<Dislike, Long> {
    @Query("SELECT l FROM Like l where l.likeableId = :likeableId AND l.likeableType = :likeableType AND l.user.id = :userId")
    Optional<Dislike> findByLikeableIdAndLikeableTypeAndUserId(@Param("likeableId") long likeableId, @Param("likeableType") LikeableType likeableType, @Param("userId") Long userId);

    @Query("SELECT COUNT(l) FROM Like l where l.likeableId = :likeableId AND l.likeableType = :likeableType")
    Long countByLikeableIdAndLikeableType(@Param("likeableId") long likeableId, @Param("likeableType") LikeableType likeableType);

    @Query("SELECT COUNT(l) FROM Like l where l.likeableId = :likeableId AND l.likeableType = :likeableType")
    List<String> findAllUsernamesByLikeableIdAndLikeableType(@Param("likeableId") long likeableId, @Param("likeableType") LikeableType likeableType);
}
