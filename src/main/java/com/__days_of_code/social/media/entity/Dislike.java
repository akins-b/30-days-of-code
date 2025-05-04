package com.__days_of_code.social.media.entity;

import com.__days_of_code.social.media.enums.LikeableType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Dislike {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;

    @Column(name = "likeable_id", nullable = false)
    long likeableId;

    @Enumerated(EnumType.STRING)
    @Column(name = "likeable_type", nullable = false)
    LikeableType likeableType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    @Column(name = "created_at", nullable = false)
    Date createdAt;
}
