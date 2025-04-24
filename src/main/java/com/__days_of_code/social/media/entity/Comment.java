package com.__days_of_code.social.media.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name= "post_id", nullable = false)
    Post post;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    Users user;

    @Column(nullable = false)
    String content;
    Date createdAt;
    Date updatedAt;
}
