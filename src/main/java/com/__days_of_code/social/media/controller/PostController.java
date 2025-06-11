package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.*;
import com.__days_of_code.social.media.dto.response.PostResponse;
import com.__days_of_code.social.media.dto.response.SharedPostResponse;
import com.__days_of_code.social.media.enums.PostStatus;
import com.__days_of_code.social.media.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Endpoint to create a new post
    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePostRequest request) {
        PostResponse postResponse = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    // Endpoint to publish a post
    @PostMapping("/publish")
    public ResponseEntity<PostResponse> publishPost(@RequestBody PublishPostRequest request) {
        PostResponse postResponse = postService.publishPost(request);
        return ResponseEntity.ok(postResponse);
    }

    // Endpoint to update an existing post
    @PutMapping("/update")
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        PostResponse postResponse = postService.updatePost(request);
        return ResponseEntity.ok(postResponse);
    }

    // Endpoint to delete a post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    //Endpoint to get all posts for a user
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts(QueryPosts request) {
        List<PostResponse> postResponses = postService.getAllPosts(request);
        return ResponseEntity.ok(postResponses);
    }

    // Endpoint to get all posts by their status
    @GetMapping("/filter/{status}")
    public ResponseEntity<List<PostResponse>> getPostsByStatus(@PathVariable PostStatus status) {
        List<PostResponse> postResponses = postService.getPostsByStatus(status);
        return ResponseEntity.ok(postResponses);
    }

    // Endpoint to share a post
    @PostMapping("/share/{postId}")
    public ResponseEntity<SharedPostResponse> sharePost(@PathVariable long postId) {
        SharedPostResponse response = postService.sharePost(postId);
        return ResponseEntity.ok(response);
    }
}
