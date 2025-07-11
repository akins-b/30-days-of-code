package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.CreateCommentRequest;
import com.__days_of_code.social.media.dto.request.QueryPostComment;
import com.__days_of_code.social.media.dto.request.QueryUserComment;
import com.__days_of_code.social.media.dto.request.UpdateCommentRequest;
import com.__days_of_code.social.media.dto.response.CommentResponse;
import com.__days_of_code.social.media.entity.Comment;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.exception.UnauthorizedException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.CommentRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    private  final ModelMapper mapper;

    public CommentService(CommentRepo commentRepo, PostRepo postRepo, UserRepo userRepo, AuthService authService, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.authService = authService;
        this.mapper = mapper;
    }

    /**
     * Creates a new comment on a post.
     *
     * @param request the request containing the comment details
     * @return the created comment response
     */
    public CommentResponse createComment(CreateCommentRequest request) {
        long userId = authService.getUserIdFromSecurityContext();
        Comment comment = new Comment();
        comment.setPost(postRepo.findById(request.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found")));
        comment.setUser(userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found")));
        comment.setContent(request.getContent());
        comment.setCreatedAt(new Date());
        commentRepo.save(comment);
        CommentResponse commentResponse = mapper.map(comment, CommentResponse.class);
        commentResponse.setUsername(comment.getUser().getUsername());
        return commentResponse;
    }

    /**
     * Updates an existing comment.
     *
     * @param request the request containing the updated comment details
     * @return the updated comment response
     */
    public CommentResponse updateComment(UpdateCommentRequest request) {
        long userId = authService.getUserIdFromSecurityContext();
        if (Objects.isNull(request)){
            throw new BadRequestException("Request cannot be null");
        }
        Comment comment = commentRepo.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (userId!= comment.getUser().getId()) {
            throw new UnauthorizedException("User not authorized to update this comment");
        }

        comment.setContent(request.getContent());
        comment.setUpdatedAt(new Date());
        commentRepo.save(comment);
        CommentResponse commentResponse = mapper.map(comment, CommentResponse.class);
        commentResponse.setUsername(comment.getUser().getUsername());
        return commentResponse;
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param commentId the ID of the comment to delete
     */
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        commentRepo.delete(comment);
    }

    /**
     * Retrieves all comments for a specific post.
     */
    public Page<CommentResponse> getAllCommentsByPost(QueryPostComment request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Comment> comments = commentRepo.findAllByPostId(request.getPostId(), pageable);
        if (comments.isEmpty()) {
            return Page.empty();
        }
        return comments.map(comment -> {
            CommentResponse response = mapper.map(comment, CommentResponse.class);
            response.setUsername(comment.getUser().getUsername());
            return response;
        });
    }

    public Page<CommentResponse> getAllCommentsByUser(QueryUserComment request) {
        long userId = authService.getUserIdFromSecurityContext();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Comment> comments = commentRepo.findAllByUserId(userId, pageable);
        if (comments.isEmpty()) {
            return Page.empty();
        }
        return comments.map(comment -> {
            CommentResponse response = mapper.map(comment, CommentResponse.class);
            response.setUsername(comment.getUser().getUsername());
            return response;
        });
    }
}
