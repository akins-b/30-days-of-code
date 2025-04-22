package com.__days_of_code.social.media.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Followers {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    Users follower;

    @ManyToOne
    @JoinColumn(name = "following_user_id", nullable = false)
    Users following;

    Date createdAt = new Date();
}
