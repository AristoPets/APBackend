package com.AristoPets.dao;

import com.AristoPets.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegRepository extends JpaRepository<UserRegistration, Long> {

    UserRegistration findByUserId(long userId);

    UserRegistration findByEmail(String email);
}
