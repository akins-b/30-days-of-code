package com.__days_of_code.social.media.controller;

import com.__days_of_code.social.media.dto.request.UpdateUserProfileRequest;
import com.__days_of_code.social.media.dto.response.UserProfileResponse;
import com.__days_of_code.social.media.service.userProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final userProfileService profileService;

    public UserProfileController(userProfileService profileService) {
        this.profileService = profileService;
    }

    // Endpoint to get the current user's profile
    @GetMapping()
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile() {
        return ResponseEntity.ok(profileService.getCurrentUserProfile());
    }

    // Endpoint to get the profile of a user by their ID
    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> viewProfile(@PathVariable String username) {
        UserProfileResponse profile = profileService.getUserProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }

    // Endpoint to update the current user's profile
    @PutMapping("/update")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequest request) {
        UserProfileResponse updatedProfile = profileService.updateUserProfile(request);
        return ResponseEntity.ok(updatedProfile);
    }
}
