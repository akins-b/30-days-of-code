package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.entity.UserRole;
import lombok.Data;

import java.util.Date;

@Data
public class RegistrationRequest {
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    UserRole role;
    boolean isVerified = false;
    String course;
    Date createdAt = new Date();
}
