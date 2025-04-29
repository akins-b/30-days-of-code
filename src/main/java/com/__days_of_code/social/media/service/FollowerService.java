package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.FollowRequest;
import com.__days_of_code.social.media.dto.response.UserResponse;
import com.__days_of_code.social.media.entity.Follower;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.repo.FollowerRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepo followerRepo;
    private final UserRepo userRepo;

    public void followUser(FollowRequest request) {
        Follower follower = new Follower();
        follower.setFollower(userRepo.findById(request.getFollowerUserId())
                .orElseThrow(() -> new EntityNotFoundException("Follower not found")));
        follower.setFollowing(userRepo.findById(request.getFollowerUserId())
                .orElseThrow(() -> new EntityNotFoundException("Following not found")));
        follower.setCreatedAt(new Date());
        followerRepo.save(follower);
    }

    public void unfollowUser(FollowRequest request){
        Follower follower = followerRepo.findById(request.getFollowingUserId())
                .orElseThrow(() -> new EntityNotFoundException("Following not found"));
        followerRepo.delete(follower);
    }

    public List<UserResponse> getAllFollowers(Long userId){
        List<Follower> followers = followerRepo.findAllByFollowerId(userId);
        return followers.stream()
                .map(follower -> new UserResponse().setUsername(follower.getFollower().getUsername()))
                .toList();
    }

    public List<UserResponse> getAllFollowing(Long userId){
        List<Follower> followings = followerRepo.findAllByFollowingId(userId);
        return followings.stream()
                .map(following -> new UserResponse().setUsername(following.getFollowing().getUsername()))
                .toList();
    }


}
