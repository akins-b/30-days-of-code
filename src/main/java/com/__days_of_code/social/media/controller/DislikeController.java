package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.DislikeRequest;
import com.__days_of_code.social.media.dto.request.QueryDislike;
import com.__days_of_code.social.media.dto.response.DislikeResponse;
import com.__days_of_code.social.media.service.DislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dislikes")
@RequiredArgsConstructor
public class DislikeController {
    private final DislikeService dislikeService;

    @GetMapping("/all")
    public ResponseEntity<DislikeResponse> getAllDislikes(@RequestBody QueryDislike request){
        return ResponseEntity.ok(dislikeService.getAllDislikes(request));
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> dislikeEntity(@RequestBody DislikeRequest request){
        dislikeService.dislikeEntity(request);
        return ResponseEntity.ok("Post disliked successfully");
    }

    @DeleteMapping("/undislike")
    public ResponseEntity<?> undislikeEntity(@RequestBody DislikeRequest request){
        dislikeService.undislikeEntity(request);
        return ResponseEntity.ok("Post undisliked successfully");
    }

}
