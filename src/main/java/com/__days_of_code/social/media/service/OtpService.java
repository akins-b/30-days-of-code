package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.entity.Otp;
import com.__days_of_code.social.media.entity.Users;
import com.__days_of_code.social.media.repo.OtpRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepo otpRepo;

    /**
     * Generates a random OTP (One-Time Password).
     *
     * @return a string representation of the generated OTP
     */
    public String generateOtp(){
        Random random = new Random();
        int otp = random.nextInt(900000);
        return String.valueOf(otp);
    }

}
