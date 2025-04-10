package com.__days_of_code.social.media.repo;

import com.__days_of_code.social.media.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
