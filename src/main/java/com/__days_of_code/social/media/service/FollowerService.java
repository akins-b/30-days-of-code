package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.FollowRequest;
import com.__days_of_code.social.media.dto.request.QueryFollowers;
import com.__days_of_code.social.media.dto.request.QueryFollowing;
import com.__days_of_code.social.media.dto.response.UserResponse;
import com.__days_of_code.social.media.entity.Follower;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.repo.FollowerRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FollowerService {
    private final FollowerRepo followerRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    public FollowerService(FollowerRepo followerRepo, UserRepo userRepo, AuthService authService, ModelMapper modelMapper) {
        this.followerRepo = followerRepo;
        this.userRepo = userRepo;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

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
     */
    public Page<UserResponse> getAllFollowers(QueryFollowers request) {
        long userId = authService.getUserIdFromSecurityContext();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Follower> followers = followerRepo.findAllByFollowingId(userId, pageable);

        if (followers.isEmpty()) {
            return Page.empty();
        }
        return followers.map(follower -> {
            UserResponse response = modelMapper.map(follower, UserResponse.class);
            return response;
        });
    }

    /**
        * Retrieves all users that a user is following.
     */
    public Page<UserResponse> getAllFollowing(QueryFollowing request) {
        long userId = authService.getUserIdFromSecurityContext();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Follower> followings = followerRepo.findAllByFollowerId(userId, pageable);

        if (followings.isEmpty()) {
            return Page.empty();
        }
        return followings.map(following -> {
            UserResponse response = modelMapper.map(following, UserResponse.class);
            return response;
        });
    }


}
