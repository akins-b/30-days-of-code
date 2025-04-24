package com.__days_of_code.social.media.controller;


import com.__days_of_code.social.media.dto.request.FollowRequest;
import com.__days_of_code.social.media.dto.response.UserResponse;
import com.__days_of_code.social.media.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/follow")
    public ResponseEntity<Void> followUser(@RequestBody FollowRequest request) {
        followerService.followUser(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestBody FollowRequest request) {
        followerService.unfollowUser(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/followers/{userId}")
    public ResponseEntity<List<UserResponse>> getAllFollowers(@PathVariable Long userId) {
        List<UserResponse> followers = followerService.getAllFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/all/following/{userId}")
    public ResponseEntity<List<UserResponse>> getAllFollowing(@PathVariable Long userId) {
        List<UserResponse> following = followerService.getAllFollowing(userId);
        return ResponseEntity.ok(following);
    }
}
