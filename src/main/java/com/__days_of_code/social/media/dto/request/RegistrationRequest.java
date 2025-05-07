package com.__days_of_code.social.media.dto.request;

import com.__days_of_code.social.media.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    // Add getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCourse() { return course; }
    public UserRole getRole() { return role; }
    public String getPassword() { return password; }
    public boolean isVerified() { return isVerified; }
    public Date getCreatedAt() { return createdAt; }

    // Add setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setCourse(String course) { this.course = course; }
    public void setRole(UserRole role) { this.role = role; }
    public void setPassword(String password) { this.password = password; }
    public void setVerified(boolean verified) { isVerified = verified; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt;}

}
