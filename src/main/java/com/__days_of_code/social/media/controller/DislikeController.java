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

    // Endpoint to get all dislikes
    @GetMapping("/all")
    public ResponseEntity<DislikeResponse> getAllDislikes(@RequestBody QueryDislike request){
        return ResponseEntity.ok(dislikeService.getAllDislikes(request));
    }

    // Endpoint to add a dislike
    @PostMapping("/add")
    public ResponseEntity<?> dislikeEntity(@RequestBody DislikeRequest request){
        dislikeService.dislikeEntity(request);
        return ResponseEntity.status(204).build();
    }

    // Endpoint to remove a dislike
    @DeleteMapping("/remove")
    public ResponseEntity<?> undislikeEntity(@RequestBody DislikeRequest request){
        dislikeService.undislikeEntity(request);
        return ResponseEntity.status(204).build();
    }

}
