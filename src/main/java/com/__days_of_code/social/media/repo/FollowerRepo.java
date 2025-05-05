package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepo extends JpaRepository<Follower, Long> {
    List<Follower> findAllByFollowerId(Long followerId);
    List<Follower> findAllByFollowingId(Long followingId);

    boolean existsByFollowerIdAndFollowingId(long followerId, long followingId);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.following.id = :followingId")
    int countByFollowingId(@Param("followingId") long followingId);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.follower.id = :followerId")
    int countByFollowerId(@Param("followerId") long followerId);


}
