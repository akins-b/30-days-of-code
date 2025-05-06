package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.LikeableType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name= "post_likes")
public class Like {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    @Column(name="likeable_id", nullable = false)
    Long likeableId;

    @Enumerated(EnumType.STRING)
    @Column(name="likeable_type", nullable = false)
    LikeableType likeableType;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    @Column(name = "created_at", nullable = false)
    Date createdAt;

    // Getters
    public long getId() {
        return id;
    }

    public long getLikeableId() {
        return likeableId;
    }

    public LikeableType getLikeableType() {
        return likeableType;
    }

    public Users getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setLikeableId(long likeableId) {
        this.likeableId = likeableId;
    }

    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
