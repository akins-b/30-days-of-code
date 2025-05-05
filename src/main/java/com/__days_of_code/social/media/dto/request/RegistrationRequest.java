package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Username is required")
    String username;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&])[A-Za-z\\d@$!#%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain at least 1 lowercase letter, 1 uppercase letter, 1 number, and 1 special character"
    )
    String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;
    String firstName;
    String lastName;
    UserRole role;
    boolean isVerified = false;
    String course;
    Date createdAt = new Date();
}
