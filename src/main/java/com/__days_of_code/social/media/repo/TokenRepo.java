package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.jwt.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
   @Query("SELECT t FROM Token t INNER JOIN Users u ON t.user.id = u.id WHERE u.id = :userId AND ( t.expired = false OR t.revoked = false)")
    List<Token> findAllByUserId(@Param("userId") long userId);

   Optional<Token> findByToken(String token);
}
