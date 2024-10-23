package com.example.web_sports.repositories;

import com.example.web_sports.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByGmail(String gmail);
    Optional<User> findByGmail(String gmail);
}