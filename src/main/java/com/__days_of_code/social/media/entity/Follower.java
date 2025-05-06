package com.__days_of_code.social.media.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    Users follower;

    @ManyToOne
    @JoinColumn(name = "following_user_id", nullable = false)
    Users following;

    Date createdAt;

    // Getters
    public long getId() {
        return id;
    }

    public Users getFollower() {
        return follower;
    }

    public Users getFollowing() {
        return following;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }

    public void setFollowing(Users following) {
        this.following = following;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
