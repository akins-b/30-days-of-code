package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.CreateCommentRequest;
import com.__days_of_code.social.media.dto.request.UpdateCommentRequest;
import com.__days_of_code.social.media.dto.response.CommentResponse;
import com.__days_of_code.social.media.entity.Comment;
import com.__days_of_code.social.media.repo.CommentRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private  final ModelMapper mapper;

    public CommentResponse createComment(CreateCommentRequest request) {
        Comment comment = new Comment();
        comment.setPost(postRepo.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found")));
        comment.setUser(userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        comment.setContent(request.getContent());
        comment.setCreatedAt(new Date());
        commentRepo.save(comment);
        CommentResponse commentResponse = mapper.map(comment, CommentResponse.class);
        commentResponse.setUsername(comment.getUser().getUsername());
        return commentResponse;
    }

    public CommentResponse updateComment(UpdateCommentRequest request) {
        Comment comment = commentRepo.findById(request.getId()).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(new Date());
        commentRepo.save(comment);
        CommentResponse commentResponse = mapper.map(comment, CommentResponse.class);
        commentResponse.setUsername(comment.getUser().getUsername());
        return commentResponse;
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
    }

    public List<CommentResponse> getAllComments(Long postId) {
        List<Comment> comments = commentRepo.findAllByPostId(postId);
        return comments.stream()
                .map(comment -> {
                    CommentResponse response = mapper.map(comment, CommentResponse.class);
                    response.setUsername(comment.getUser().getUsername());
                    return response;
                })
                .toList();
    }
}
