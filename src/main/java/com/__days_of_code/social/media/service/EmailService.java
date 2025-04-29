package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.exception.EmailServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOtp(String userEmail, String otp){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject("Email verification OTP");
            helper.setText("Your OTP for email verification is: " + otp);
            mailSender.send(message);
        }
        catch (MessagingException e) {
            throw new EmailServiceException("Failed to send email", e);
        }
    }
}
