package com.__days_of_code.social.media.entity;

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

    @Column(name = "likeable_type", nullable = false)
    String likeableType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    @Column(name = "created_at", nullable = false)
    Date createdAt;
}
