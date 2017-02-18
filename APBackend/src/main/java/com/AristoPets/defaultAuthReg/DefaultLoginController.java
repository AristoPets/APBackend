package com.AristoPets.defaultAuthReg;

import com.AristoPets.entity.User;
import com.AristoPets.entity.UserRegistration;
import com.AristoPets.services.CookieService;
import com.AristoPets.services.UserRegService;
import com.AristoPets.services.UserService;
import com.AristoPets.webconfig.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultLoginController {

    private final CookieService cookieService;
    private final UserRegService userRegService;
    private final UserService userService;

    @Autowired
    public DefaultLoginController(CookieService cookieService, UserRegService userRegService, UserService userService) {
        this.cookieService = cookieService;
        this.userRegService = userRegService;
        this.userService = userService;
    }


    @PostMapping(value = "/login")
    public String authUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String redirectUri = request.getParameter("redirectUri");
        redirectUri = redirectUri != null ? redirectUri : "/";
        if (pass == null || email == null ||
                pass.length() < 6 || email.length() < 6) {
            return "redirect:" + redirectUri + "?needAuth=true";
        }

        boolean remember = request.getParameter("remember") != null;
        String hashedPass = new Encoder(pass).getHash();
        UserRegistration existingUser = userRegService.getPasswordByEmail(email);
        if (existingUser == null || !(existingUser.getPassword().equals(hashedPass))) {
            return "redirect:" + redirectUri + "?needAuth=true";
        }

        User user = userService.getUser(existingUser.getUserId());
        String uuid = cookieService.setCookie(user);
        if (remember) {
            CookieHelper.addCookie(response, CookieHelper.authCookie, uuid, CookieHelper.MAX_AGE);
        } else {
            CookieHelper.addSessionCookie(response, CookieHelper.authCookie, uuid);
        }
        return "redirect:" + redirectUri;
    }
}
