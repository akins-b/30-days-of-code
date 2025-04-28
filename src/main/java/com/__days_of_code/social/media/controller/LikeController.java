package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.LikeRequest;
import com.__days_of_code.social.media.dto.request.QueryLike;
import com.__days_of_code.social.media.dto.response.LikeResponse;
import com.__days_of_code.social.media.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/all")
    public ResponseEntity<LikeResponse> getAllLikes(@RequestBody QueryLike request){
        return ResponseEntity.ok(likeService.getAllLikes(request));
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeEntity(@RequestBody LikeRequest request){
        likeService.likeEntity(request);
        return ResponseEntity.ok("Post liked successfully");
    }

    @PostMapping("/unlike")
    public ResponseEntity<?> unlikeEntity(@RequestBody LikeRequest request){
        likeService.unlikeEntity(request);
        return ResponseEntity.ok("Post unliked successfully");
    }
}
