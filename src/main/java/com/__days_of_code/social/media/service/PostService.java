package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.*;
import com.__days_of_code.social.media.dto.response.PostResponse;
import com.__days_of_code.social.media.dto.response.SharedPostResponse;
import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.enums.PostStatus;
import com.__days_of_code.social.media.exception.EntityNotFoundException;
import com.__days_of_code.social.media.exception.UnauthorizedException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    private final ModelMapper mapper;


    /**
     * Creates a new post.
     *
     * @param request the request containing the post details
     * @return the created post response
     */
    public PostResponse createPost(CreatePostRequest request) {
        long userId = authService.getUserIdFromSecurityContext();
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setMediaLink(request.getMediaLink());
        post.setUser(userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found")));
        post.setCreatedAt(new Date());
        post.setStatus(PostStatus.DRAFT);
        Post savedPost = postRepo.save(post);
        return mapper.map(savedPost, PostResponse.class);
    }

    /**
     * Publishes a post.
     *
     * @param request the request containing the post ID and user ID
     * @return the published post response
     */
    public PostResponse publishPost(PublishPostRequest request) {
        long userId = authService.getUserIdFromSecurityContext();
        Post post = postRepo.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        if (post.getUser().getId() != userId) {
            throw new UnauthorizedException("User not authorized to publish this post");
        }
        post.setStatus(PostStatus.PUBLISHED);
        post.setPublishedAt(new Date());
        Post publishedPost = postRepo.save(post);
        return mapper.map(publishedPost, PostResponse.class);
    }

    /**
     * Updates an existing post.
     *
     * @param request the request containing the updated post details
     * @return the updated post response
     */
    public PostResponse updatePost(UpdatePostRequest request){
        long userId = authService.getUserIdFromSecurityContext();
        Post post = postRepo.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        // Check if the user is authorized to update the post
        if (post.getUser().getId() != userId) {
            throw new UnauthorizedException("User not authorized to update this post");
        }

        if(request.getTitle() != null){
            post.setTitle(request.getTitle());
        }

        if(request.getBody() != null){
            post.setBody(request.getBody());
        }

        if(request.getMediaLink() != null){
            post.setMediaLink(request.getMediaLink());
        }

        post.setUpdatedAt(new Date());
        postRepo.save(post);
        return mapper.map(post, PostResponse.class);
    }

    /**
     * Deletes a post by its ID.
     *
     * @param postId the ID of the post to delete
     */
    public void deletePost(Long postId){
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        postRepo.delete(post);
    }

    /**
     * Retrieves all posts for a user.
     *
     * @return a list of post responses for the user
     */
    public List<PostResponse> getAllPosts(){
        long userId = authService.getUserIdFromSecurityContext();
        List<Post> posts = postRepo.findAllByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("No posts found for user ID: " + userId));
        return posts.stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .toList();
    }

    /**
     * Retrieves posts by their status.
     *
     * @param status the status of the posts to retrieve
     * @return a list of post responses with the specified status
     */
    public List<PostResponse> getPostsByStatus(PostStatus status){
        long userId = authService.getUserIdFromSecurityContext();
        List<Post> posts = postRepo.findAllByUserIdAndStatus(userId, status)
                .orElseThrow(() -> new EntityNotFoundException("No posts found with status: " + status));
        return posts.stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .toList();
    }

    public SharedPostResponse sharePost(long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        post.setShareCount(post.getShareCount() + 1);
        postRepo.save(post);
        return mapper.map(post, SharedPostResponse.class);
    }

}
