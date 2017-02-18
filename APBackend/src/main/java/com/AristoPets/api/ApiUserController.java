package com.AristoPets.api;


import com.AristoPets.dto.UserDto;
import com.AristoPets.entity.User;
import com.AristoPets.services.UserService;
import com.AristoPets.webconfig.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    private final UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("/")
    public ResponseEntity<?> updateUser(@RequestBody UserDto updatedUser, HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        updatedUser.setId(user.getId());
        return ResponseEntity.ok(userService.update(updatedUser));
    }

    @GetMapping("/")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDto userDto = userService.getUserWithFullInfo(user.getId());
        return ResponseEntity.ok(userDto);
    }
}
