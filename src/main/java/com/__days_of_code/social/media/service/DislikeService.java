package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.DislikeRequest;
import com.__days_of_code.social.media.dto.request.QueryDislike;
import com.__days_of_code.social.media.dto.response.DislikeResponse;
import com.__days_of_code.social.media.entity.Comment;
import com.__days_of_code.social.media.entity.Dislike;
import com.__days_of_code.social.media.entity.Like;
import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.CommentRepo;
import com.__days_of_code.social.media.repo.DislikeRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DislikeService {
    private final DislikeRepo dislikeRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public DislikeResponse getAllDislikes(QueryDislike request) {
        if(Objects.isNull(request)) {
            throw new BadRequestException("Request cannot be null");
        }
        DislikeResponse dislikeResponse = new DislikeResponse();

        if (request.getLikeableType().equalsIgnoreCase("post")){
            Post post = postRepo.findById(request.getLikeableId())
                    .orElseThrow(()-> new EntityNotFoundException("Post not found"));
            dislikeResponse.setLikeableId(post.getId());
            dislikeResponse.setLikeableType(request.getLikeableType());
            dislikeResponse.setDislikeTotalCount(dislikeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            dislikeResponse.setUsernames(dislikeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return dislikeResponse;
        }
        else if (request.getLikeableType().equalsIgnoreCase("comment")){
            Comment comment = commentRepo.findById(request.getLikeableId())
                    .orElseThrow(()->new EntityNotFoundException("Comment not found"));
            dislikeResponse.setLikeableId(comment.getId());
            dislikeResponse.setLikeableType(request.getLikeableType());
            dislikeResponse.setDislikeTotalCount(dislikeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            dislikeResponse.setUsernames(dislikeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return dislikeResponse;
        }
        else {
            throw new BadRequestException("Invalid likeable type");
        }
    }

    public void dislikeEntity(DislikeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Field cannot be null");
        }
        Dislike dislike = new Dislike();

        if (request.getLikeableType().equalsIgnoreCase("post")) {
            Post post = postRepo.findById(request.getLikeableId())
                    .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            dislike.setLikeableId(post.getId());
            dislike.setLikeableType(request.getLikeableType());
            dislike.setUser(userRepo.findById(request.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found")));
            dislike.setCreatedAt(new Date());
            dislikeRepo.save(dislike);
        }
        else if (request.getLikeableType().equalsIgnoreCase("comment")) {
            Comment comment = commentRepo.findById(request.getLikeableId())
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
            dislike.setLikeableId(comment.getId());
            dislike.setLikeableType(request.getLikeableType());
            dislike.setUser(userRepo.findById(request.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found")));
            dislike.setCreatedAt(new Date());
            dislikeRepo.save(dislike);
        } else {
            throw new BadRequestException("Invalid likeable type");
        }
    }

    public void undislikeEntity(DislikeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Field cannot be null");
        }
        Dislike dislike = dislikeRepo.findByLikeableIdAndLikeableTypeAndUserId(request.getLikeableId(), request.getLikeableType(), request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Dislike not found"));

        dislikeRepo.delete(dislike);
    }
}
