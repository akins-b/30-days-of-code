package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.LikeRequest;
import com.__days_of_code.social.media.dto.request.QueryLike;
import com.__days_of_code.social.media.dto.response.LikeResponse;
import com.__days_of_code.social.media.entity.Comment;
import com.__days_of_code.social.media.entity.Like;
import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.repo.CommentRepo;
import com.__days_of_code.social.media.repo.LikeRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public LikeResponse getAllLikes(QueryLike request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        LikeResponse likeResponse = new LikeResponse();

        if (request.getLikeableType().equalsIgnoreCase("post")){
            Post post = postRepo.findById(request.getLikeableId()).orElseThrow(()-> new RuntimeException("Post not found"));

            likeResponse.setLikeableId(post.getId());
            likeResponse.setLikeableType(request.getLikeableType());
            likeResponse.setTotalLikes(likeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            likeResponse.setUsernames(likeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return likeResponse;
        }
        else if (request.getLikeableType().equalsIgnoreCase("comment")){
            Comment comment = commentRepo.findById(request.getLikeableId()).orElseThrow(()-> new RuntimeException("Comment not found"));
            likeResponse.setLikeableId(comment.getId());
            likeResponse.setLikeableType(request.getLikeableType());
            likeResponse.setTotalLikes(likeRepo.countByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            likeResponse.setUsernames(likeRepo.findAllUsernamesByLikeableIdAndLikeableType(request.getLikeableId(), request.getLikeableType()));
            return likeResponse;
        }
        else {
            throw new IllegalArgumentException("Invalid likeable type");
        }
    }

    public void likeEntity(LikeRequest request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        Like like = new Like();

        if (request.getLikeableType().equalsIgnoreCase("post")){
            Post post = postRepo.findById(request.getLikeableId()).orElseThrow(()-> new RuntimeException("Post not found"));

            like.setLikeableId(post.getId());
            like.setLikeableType(request.getLikeableType());
            like.setUser(userRepo.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found")));
            like.setCreatedAt(new Date());
            likeRepo.save(like);
        }
        else if (request.getLikeableType().equalsIgnoreCase("comment")){
            Comment comment = commentRepo.findById(request.getLikeableId()).orElseThrow(()-> new RuntimeException("Comment not found"));
            like.setLikeableId(comment.getId());
            like.setLikeableType(request.getLikeableType());
            like.setUser(userRepo.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found")));
            like.setCreatedAt(new Date());
            likeRepo.save(like);
        }
        else {
            throw new IllegalArgumentException("Invalid likeable type");
        }
    }

    public void unlikeEntity(LikeRequest request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        Like like = likeRepo.findByLikeableIdAndLikeableTypeAndUserId(request.getLikeableId(), request.getLikeableType(), request.getUserId())
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepo.delete(like);
    }
}
