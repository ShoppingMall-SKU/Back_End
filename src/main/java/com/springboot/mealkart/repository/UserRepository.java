package com.springboot.mealkart.repository;

import com.springboot.mealkart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);
    Optional<User> findByEmail(String email);
}
