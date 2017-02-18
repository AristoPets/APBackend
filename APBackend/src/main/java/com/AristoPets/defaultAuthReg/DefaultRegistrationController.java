package com.AristoPets.defaultAuthReg;

import com.AristoPets.dto.RegistrationDto;
import com.AristoPets.entity.User;
import com.AristoPets.entity.UserRegistration;
import com.AristoPets.entity.enums.AuthType;
import com.AristoPets.entity.enums.UserType;
import com.AristoPets.mailSender.NotificationService;
import com.AristoPets.services.CookieService;
import com.AristoPets.services.UserRegService;
import com.AristoPets.services.UserService;
import com.AristoPets.webconfig.CookieHelper;
import com.AristoPets.webconfig.URLS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultRegistrationController {

    private final UserService userService;
    private final CookieService cookieService;
    private final UserRegService userRegService;
    private final NotificationService notificationService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public DefaultRegistrationController(UserService userService, CookieService cookieService,
                                         UserRegService userRegService, NotificationService notificationService) {
        this.userService = userService;
        this.cookieService = cookieService;
        this.userRegService = userRegService;
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/registration")
    public String regNewUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        User oldUser = (User) request.getAttribute("userData");
        if (oldUser != null) {
            CookieHelper.removeCookie(response, CookieHelper.authCookie);
        }

        String redirectUri = request.getParameter("redirectUri");
        redirectUri = redirectUri != null ? redirectUri : "/";

        UserRegistration registeredUser;
        RegistrationDto registration = RegistrationDto.requestToRegisteredUser(request);
        String invalidUserInput = registration.getInvalidUserInput();
        if (invalidUserInput == null) {
            registeredUser = userRegService.getPasswordByEmail(registration.getEmail());
            if (registeredUser != null) {
                return "redirect:" + redirectUri + "?invalidReg=User already exists";
            }
        } else {
            return "redirect:" + redirectUri + "?invalidReg=" + invalidUserInput;
        }

        String hashedPass = new Encoder(registration.getPassword()).getHash();
        User newUser = new User();
        UserRegistration userReg = new UserRegistration();
        newUser.setFirstName(registration.getFirstName());
        newUser.setLastName(registration.getLastName());
        newUser.setEmail(registration.getEmail());
        newUser.setAuthType(AuthType.EMAIL);
        newUser.setUserType(UserType.USER);
        newUser.setPhoto("/img/notAuth.png");
        userService.save(newUser);
        userReg.setEmail(registration.getEmail());
        userReg.setPassword(hashedPass);
        userReg.setUserId(newUser.getId());
        userRegService.save(userReg);
        try {
            notificationService.sendNotificationOfRegistration(registration.getEmail());
        } catch (MailException me) {
            logger.error("Error in mail sending :", me.getMessage());
        }
        String uuid = cookieService.setCookie(newUser);
        CookieHelper.addCookie(response, CookieHelper.authCookie, uuid, CookieHelper.MAX_AGE);

        return "redirect:" + URLS.USER_PROFILE;
    }

}
