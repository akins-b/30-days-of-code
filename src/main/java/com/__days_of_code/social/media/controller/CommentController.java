package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.CreateCommentRequest;
import com.__days_of_code.social.media.dto.request.UpdateCommentRequest;
import com.__days_of_code.social.media.dto.response.CommentResponse;
import com.__days_of_code.social.media.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // Endpoint to create a new comment
    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CreateCommentRequest request){
        CommentResponse commentResponse = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    // Endpoint to update an existing comment
    @PutMapping("/update")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody UpdateCommentRequest request){
        CommentResponse commentResponse = commentService.updateComment(request);
        return ResponseEntity.ok(commentResponse);
    }

    // Endpoint to delete a comment by its ID
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get a comment by its post ID
    @GetMapping("/all/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsByPost(@PathVariable Long postId){
        List<CommentResponse> commentResponses = commentService.getAllCommentsByPost(postId);
        return ResponseEntity.ok(commentResponses);
    }

    // Endpoint to get a comment by its user ID
    @GetMapping("/all/user")
    public ResponseEntity<List<CommentResponse>> getAllCommentsByUser(){
        List<CommentResponse> commentResponses = commentService.getAllCommentsByUser();
        return ResponseEntity.ok(commentResponses);
    }
}
