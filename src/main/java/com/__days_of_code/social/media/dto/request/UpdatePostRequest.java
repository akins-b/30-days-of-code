package com.__days_of_code.social.media.dto.request;


public class UpdatePostRequest {
    long id;
    String title;
    String body;
    String mediaLink;

    // Getters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }
}
