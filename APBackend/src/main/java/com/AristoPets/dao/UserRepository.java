package com.AristoPets.dao;

import com.AristoPets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAuthId(String authId);

    User findByEmail(String email);
}
