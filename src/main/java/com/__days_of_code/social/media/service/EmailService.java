package com.__days_of_code.social.media.service;

import com.__days_of_code.social.media.exception.EmailServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends an OTP to the user's email for verification.
     *
     * @param userEmail the email address of the user
     * @param otp       the OTP to be sent
     */
    public void sendOtp(String userEmail, String otp){
        // Validate the email format
        if (!EMAIL_PATTERN.matcher(userEmail).matches()) {
            throw new EmailServiceException("Invalid email format");
        }

        // Attempt to send the email
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
