package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.auth.AuthService;
import com.__days_of_code.social.media.dto.request.UpdateUserProfileRequest;
import com.__days_of_code.social.media.dto.response.UserProfileResponse;
import com.__days_of_code.social.media.entity.UserProfile;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.exception.BadRequestException;
import com.__days_of_code.social.media.exception.UserNotFoundException;
import com.__days_of_code.social.media.repo.FollowerRepo;
import com.__days_of_code.social.media.repo.PostRepo;
import com.__days_of_code.social.media.repo.UserProfileRepo;
import com.__days_of_code.social.media.repo.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userProfileService {

    private final AuthService authService;
    private final UserRepo userRepo;
    private final FollowerRepo followerRepo;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final UserProfileRepo userProfileRepo;

    public UserProfileResponse getCurrentUserProfile() {
        long userId = authService.getUserIdFromSecurityContext();
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        int postCount = postRepo.countByUserId(userId);
        int followersCount = followerRepo.countByFollowingId(userId);
        int followingCount = followerRepo.countByFollowerId(userId);

        UserProfileResponse userProfileResponse = modelMapper.map(user, UserProfileResponse.class);
        userProfileResponse.setNumberOfPosts(postCount);
        userProfileResponse.setNumberOfFollowers(followersCount);
        userProfileResponse.setNumberOfFollowing(followingCount);
        userProfileResponse.setBio(user.getUserProfile().getBio());
        userProfileResponse.setProfilePictureUrl(user.getUserProfile().getProfilePictureUrl());

        return userProfileResponse;
    }

    public UserProfileResponse getUserProfileByUsername(String username) {
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        int postCount = postRepo.countByUserId(user.getId());
        int followersCount = followerRepo.countByFollowingId(user.getId());
        int followingCount = followerRepo.countByFollowerId(user.getId());

        UserProfileResponse userProfileResponse = modelMapper.map(user, UserProfileResponse.class);
        userProfileResponse.setNumberOfPosts(postCount);
        userProfileResponse.setNumberOfFollowers(followersCount);
        userProfileResponse.setNumberOfFollowing(followingCount);
        userProfileResponse.setBio(user.getUserProfile().getBio());
        userProfileResponse.setProfilePictureUrl(user.getUserProfile().getProfilePictureUrl());

        return userProfileResponse;
    }

    public UserProfileResponse updateUserProfile(@Valid UpdateUserProfileRequest request) {
        long userId = authService.getUserIdFromSecurityContext();
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));



        UserProfile userProfile = userProfileRepo.findByUserId(user.getId())
                .orElseThrow(() -> new BadRequestException("User profile not found"));

        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }

        if (request.getBio() != null) {
            userProfile.setBio(request.getBio());
        }
        if (request.getProfilePictureUrl() != null) {
            userProfile.setProfilePictureUrl(request.getProfilePictureUrl());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        userProfileRepo.save(userProfile);
        userRepo.save(user);

        UserProfileResponse response = new UserProfileResponse();
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setBio(userProfile.getBio());
        response.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        response.setNumberOfPosts(postRepo.countByUserId(user.getId()));
        response.setNumberOfFollowers(followerRepo.countByFollowingId(user.getId()));
        response.setNumberOfFollowing(followerRepo.countByFollowerId(user.getId()));
        return response;
    }
}
