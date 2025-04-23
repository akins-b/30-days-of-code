package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.CreatePostRequest;
import com.__days_of_code.social.media.dto.request.UpdatePostRequest;
import com.__days_of_code.social.media.dto.response.PostResponse;
import com.__days_of_code.social.media.entity.PostStatus;
import com.__days_of_code.social.media.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePostRequest request) {
        PostResponse postResponse = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @PostMapping("/publish/{postId}")
    public ResponseEntity<PostResponse> publishPost(@PathVariable Long postId) {
        PostResponse postResponse = postService.publishPost(postId);
        return ResponseEntity.ok(postResponse);
    }


    @PutMapping("/update")
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        PostResponse postResponse = postService.updatePost(request);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> postResponses = postService.getAllPosts();
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/filter/{status}")
    public ResponseEntity<List<PostResponse>> getPostsByStatus(@PathVariable PostStatus status) {
        List<PostResponse> postResponses = postService.getPostsByStatus(status);
        return ResponseEntity.ok(postResponses);
    }
}
