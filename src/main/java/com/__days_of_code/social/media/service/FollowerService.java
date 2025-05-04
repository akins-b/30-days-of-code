package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.FollowRequest;
import com.__days_of_code.social.media.dto.response.UserResponse;
import com.__days_of_code.social.media.entity.Follower;
import com.__days_of_code.social.media.exception.BadRequestException;
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
    private final AuthService authService;

    /**
     * Follows a user.
     *
     * @param request the request containing the follower and following user IDs
     */
    public void followUser(FollowRequest request) {
        long userId = authService.getUserIdFromSecurityContext();

        if (userId == request.getFollowingUserId()) {
            throw new BadRequestException("A user cannot follow themselves.");
        }
        if (followerRepo.existsByFollowerIdAndFollowingId(userId, request.getFollowingUserId())) {
            throw new BadRequestException("You are already following this user.");
        }
        Follower follower = new Follower();
        follower.setFollower(userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Follower not found")));
        follower.setFollowing(userRepo.findById(request.getFollowingUserId())
                .orElseThrow(() -> new EntityNotFoundException("Following not found")));
        follower.setCreatedAt(new Date());
        followerRepo.save(follower);
    }

    /**
     * Unfollows a user.
     *
     * @param request the request containing the follower and following user IDs
     */
    public void unfollowUser(FollowRequest request){
        Follower follower = followerRepo.findById(request.getFollowingUserId())
                .orElseThrow(() -> new EntityNotFoundException("Following not found"));
        followerRepo.delete(follower);
    }

    /**
     * Retrieves all followers of a user.
     *
     * @return a list of followers
     */
    public List<UserResponse> getAllFollowers(){
        long userId = authService.getUserIdFromSecurityContext();
        List<Follower> followers = followerRepo.findAllByFollowingId(userId);
        return followers.stream()
                .map(follower -> new UserResponse().setUsername(follower.getFollower().getUsername()))
                .toList();
    }

    /**
     * Retrieves all users that a user is following.
     *
     * @return a list of users that the user is following
     */
    public List<UserResponse> getAllFollowing(){
        long userId = authService.getUserIdFromSecurityContext();
        List<Follower> followings = followerRepo.findAllByFollowerId(userId);
        return followings.stream()
                .map(following -> new UserResponse().setUsername(following.getFollowing().getUsername()))
                .toList();
    }


}
