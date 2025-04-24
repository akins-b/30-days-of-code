package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepo extends JpaRepository<Follower, Long> {
    List<Follower> findAllByFollowerId(Long followerId);
    List<Follower> findAllByFollowingId(Long followingId);
}
