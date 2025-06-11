package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Follower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepo extends JpaRepository<Follower, Long> {
    Page<Follower> findAllByFollowerId(Long followerId, Pageable pageable);
    Page<Follower> findAllByFollowingId(Long followingId, Pageable pageable);

    boolean existsByFollowerIdAndFollowingId(long followerId, long followingId);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.following.id = :followingId")
    int countByFollowingId(@Param("followingId") long followingId);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.follower.id = :followerId")
    int countByFollowerId(@Param("followerId") long followerId);



}
