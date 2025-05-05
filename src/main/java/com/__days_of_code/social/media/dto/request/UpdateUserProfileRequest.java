package com.__days_of_code.social.media.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UpdateUserProfileRequest {
    String username;

    @Size(max=150)
    String bio;

    String profilePictureUrl;
}
