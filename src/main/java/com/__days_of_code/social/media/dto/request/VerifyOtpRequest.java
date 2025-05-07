package com.__days_of_code.social.media.dto.request;

public class VerifyOtpRequest {
    String email;
    String otp;

    // Getters
    public String getEmail() {
        return email;
    }
    public String getOtp() {
        return otp;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
}
