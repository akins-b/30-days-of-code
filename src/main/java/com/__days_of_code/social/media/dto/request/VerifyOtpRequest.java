package com.__days_of_code.social.media.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VerifyOtpRequest {
    long userId;
    String otp;
}
