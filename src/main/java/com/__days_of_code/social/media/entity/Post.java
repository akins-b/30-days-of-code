package com.__days_of_code.social.media.entity;

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
}
