package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.dto.request.CreatePostRequest;
import com.__days_of_code.social.media.dto.request.UpdatePostRequest;
import com.__days_of_code.social.media.dto.response.PostResponse;
import com.__days_of_code.social.media.entity.Post;
import com.__days_of_code.social.media.entity.PostStatus;
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
    private final ModelMapper mapper;


    public PostResponse createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setMediaLink(request.getMediaLink());
        post.setUser(userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        post.setCreatedAt(new Date());
        post.setStatus(PostStatus.DRAFT);
        Post savedPost = postRepo.save(post);
        return mapper.map(savedPost, PostResponse.class);
    }

    public PostResponse publishPost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setStatus(PostStatus.PUBLISHED);
        post.setPublishedAt(new Date());
        Post publishedPost = postRepo.save(post);
        return mapper.map(publishedPost, PostResponse.class);
    }

    public PostResponse updatePost(UpdatePostRequest request){
        Post post = postRepo.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setMediaLink(request.getMediaLink());
        post.setUpdatedAt(new Date());
        Post updatedPost = postRepo.save(post);
        return mapper.map(updatedPost, PostResponse.class);
    }

    public void deletePost(Long postId){
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepo.delete(post);
    }

    public List<PostResponse> getAllPosts(){
        List<Post> posts = postRepo.findAll();
        return posts.stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .toList();
    }

    public List<PostResponse> getPostsByStatus(PostStatus status){
        List<Post> posts = postRepo.findByStatus(status)
                .orElseThrow(() -> new RuntimeException("No posts found with status: " + status));
        return posts.stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .toList();
    }

}
