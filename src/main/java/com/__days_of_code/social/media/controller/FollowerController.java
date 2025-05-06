package com.__days_of_code.social.media.controller;


import com.__days_of_code.social.media.dto.request.FollowRequest;
import com.__days_of_code.social.media.dto.response.UserResponse;
import com.__days_of_code.social.media.service.FollowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowerController {
    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }
    // Endpoint to follow a user
    @PostMapping("/follow")
    public ResponseEntity<Void> followUser(@RequestBody FollowRequest request) {
        followerService.followUser(request);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to unfollow a user
    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestBody FollowRequest request) {
        followerService.unfollowUser(request);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to see all followers of a user
    @GetMapping("/all/followers")
    public ResponseEntity<List<UserResponse>> getAllFollowers() {
        List<UserResponse> followers = followerService.getAllFollowers();
        return ResponseEntity.ok(followers);
    }

    // Endpoint to see all users that a user is following
    @GetMapping("/all/following")
    public ResponseEntity<List<UserResponse>> getAllFollowing() {
        List<UserResponse> following = followerService.getAllFollowing();
        return ResponseEntity.ok(following);
    }
}
