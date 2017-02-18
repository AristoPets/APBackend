package com.AristoPets.api;


import com.AristoPets.entity.User;
import com.AristoPets.entity.enums.UserType;
import com.AristoPets.services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiUserBreeder {

    private UserService userService;

    @Autowired
    public ApiUserBreeder(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/api/breeder/confirm/{userId}")
    public ResponseEntity<?> confirmIsUserBreeder(HttpServletRequest request, @PathVariable("userId") Long userId) {
        User user = (User) request.getAttribute("userData");

        if (user == null || userId == null || (user.getId() != userId)) {
            return ResponseEntity.badRequest().build();
        }
        user.setContractOfSale(true);
        user.setUserType(UserType.BREEDER);
        User breeder = userService.save(user);
        return ResponseEntity.ok(breeder);
    }
}
