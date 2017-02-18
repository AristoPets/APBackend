package com.AristoPets.services;

import com.AristoPets.dao.UserRegRepository;
import com.AristoPets.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PasswordService")
@Transactional
public class UserRegService {

    private final UserRegRepository userRegRepository;

    @Autowired
    public UserRegService(UserRegRepository userRegRepository) {
        this.userRegRepository = userRegRepository;
    }


    public UserRegistration getPassword(long id) {
        return userRegRepository.findOne(id);
    }

    public UserRegistration save(UserRegistration userRegistration) {
        return userRegRepository.saveAndFlush(userRegistration);
    }

    public UserRegistration getPasswordByUserId(long userId) {
        return userRegRepository.findByUserId(userId);
    }

    public UserRegistration getPasswordByEmail(String email) {
        return userRegRepository.findByEmail(email);
    }
}
