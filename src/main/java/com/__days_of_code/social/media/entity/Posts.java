package com.__days_of_code.social.media.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Posts {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;
    String title;
    String body;
    long userId;
    String status;
    String mediaLink;
    Date createdAt;
    Date updatedAt;
}
