package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Post implements Likeable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;
    String title;
    String body;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;
    PostStatus status;
    String mediaLink;
    Date createdAt;
    Date publishedAt;
    Date updatedAt;

    @Column(nullable = false, columnDefinition = "integer default 0")
    int shareCount;

    @Override
    public long getId() {
        return id;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Users getUser() {
        return user;
    }

    public PostStatus getStatus() {
        return status;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getShareCount() {
        return shareCount;
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

    public void setUser(Users user) {
        this.user = user;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
}
