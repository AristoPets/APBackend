package com.AristoPets.services;

import com.AristoPets.dto.UserDto;
import com.AristoPets.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User getUser(long id);

    User save(User user);

    User getUserByAuthId(String authId);

    UserDto getUserWithFullInfo(long userId);

    User update(UserDto userDto);

    User getUserByEmail(String email);

}
