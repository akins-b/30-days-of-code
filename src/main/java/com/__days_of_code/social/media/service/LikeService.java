package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.LikeRequest;
import com.__days_of_code.social.media.dto.request.QueryLike;
import com.__days_of_code.social.media.dto.response.LikeResponse;
import com.__days_of_code.social.media.entity.Comment;
import com.__days_of_code.social.media.entity.Like;
import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.enums.LikeableType;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.CommentRepo;
import com.__days_of_code.social.media.repo.LikeRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class LikeService {
    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final AuthService authService;

    public LikeService(LikeRepo likeRepo, PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo, AuthService authService) {
        this.likeRepo = likeRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.authService = authService;
    }

    /**
     * Retrieves all likes for a given entity (post or comment).
     *
     * @param request the request containing the entity type and ID
     * @return the response containing the total like count and usernames of users who liked
     */
    public LikeResponse getAllLikes(QueryLike request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request cannot be null");
        }

        LikeResponse likeResponse = new LikeResponse();

        if (request.getLikeableType() == LikeableType.POST) {
            Post post = postRepo.findById(request.getLikeableId()).orElseThrow(()-> new EntityNotFoundException("Post not found"));

            likeResponse.setLikeableId(post.getId());
            likeResponse.setLikeableType(request.getLikeableType());
            likeResponse.setTotalLikes(likeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            likeResponse.setUsernames(likeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return likeResponse;
        }
        else if (request.getLikeableType() == LikeableType.COMMENT) {
            Comment comment = commentRepo.findById(request.getLikeableId()).orElseThrow(()-> new EntityNotFoundException("Comment not found"));
            likeResponse.setLikeableId(comment.getId());
            likeResponse.setLikeableType(request.getLikeableType());
            likeResponse.setTotalLikes(likeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            likeResponse.setUsernames(likeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return likeResponse;
        }
        else {
            throw new BadRequestException("Invalid likeable type");
        }
    }

    /**
     * Likes a post or comment.
     *
     * @param request the request containing the likeable type, ID, and user ID
     */
    public void likeEntity(LikeRequest request) {
        // Get the user ID from the security context
        long userId = authService.getUserIdFromSecurityContext();

        if (Objects.isNull(request)) {
            throw new BadRequestException("Field cannot be null");
        }

        Like like = new Like();

        if (request.getLikeableType() == LikeableType.POST){
            Post post = postRepo.findById(request.getLikeableId()).orElseThrow(()-> new EntityNotFoundException("Post not found"));

            like.setLikeableId(post.getId());
            like.setLikeableType(request.getLikeableType());
            like.setUser(userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found")));
            like.setCreatedAt(new Date());
            likeRepo.save(like);
        }
        else if (request.getLikeableType() == LikeableType.COMMENT){
            Comment comment = commentRepo.findById(request.getLikeableId()).orElseThrow(()-> new EntityNotFoundException("Comment not found"));
            like.setLikeableId(comment.getId());
            like.setLikeableType(request.getLikeableType());
            like.setUser(userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found")));
            like.setCreatedAt(new Date());
            likeRepo.save(like);
        }
        else {
            throw new BadRequestException("Invalid likeable type");
        }
    }

    /**
     * Removes a like on a post or comment.
     *
     * @param request the request containing the likeable type, ID, and user ID
     */
    public void unlikeEntity(LikeRequest request) {
        // Get the user ID from the security context
        long userId = authService.getUserIdFromSecurityContext();

        if (Objects.isNull(request)) {
            throw new BadRequestException("Field cannot be null");
        }

        Like like = likeRepo.findByLikeableIdAndLikeableTypeAndUserId(request.getLikeableId(), request.getLikeableType(), userId)
                .orElseThrow(() -> new EntityNotFoundException("Like not found"));

        likeRepo.delete(like);
    }
}
