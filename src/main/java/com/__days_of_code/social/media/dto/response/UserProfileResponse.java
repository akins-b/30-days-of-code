package com.__days_of_code.social.media.dto.response;

public class UserProfileResponse {
    String firstName;
    String lastName;
    String username;
    String bio;
    String profilePictureUrl;
    int numberOfPosts;
    int numberOfFollowers;
    int numberOfFollowing;

    // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    public int getNumberOfPosts() {
        return numberOfPosts;
    }
    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }
    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }
    public void setNumberOfFollowing(int numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }
}
