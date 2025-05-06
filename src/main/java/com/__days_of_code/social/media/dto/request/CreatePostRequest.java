package com.__days_of_code.social.media.dto.request;

public class CreatePostRequest {
    String title;
    String body;
    String mediaLink;

    //Getters
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public String getMediaLink() {
        return mediaLink;
    }
}
