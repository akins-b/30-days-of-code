package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepo extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUserIdAndOtp(long userId, String otp);
}
