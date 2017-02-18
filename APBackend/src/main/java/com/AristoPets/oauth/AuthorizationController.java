package com.AristoPets.oauth;

import com.AristoPets.webconfig.CookieHelper;
import com.AristoPets.entity.enums.AuthType;
import com.AristoPets.services.CookieService;
import com.AristoPets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthorizationController {

    private final AuthScribeConfig authScribeConfig;

    @Autowired
    public AuthorizationController(AuthScribeConfig authScribeConfig) {
        this.authScribeConfig = authScribeConfig;
    }


    @GetMapping(value = "/authtype/{id}")
    public String chooseSocialAuthType(@PathVariable(value = "id") int id) {
        String authUrl = "/";
        if (id == 1) {
            authUrl = authScribeConfig.getAuthUrl(AuthType.FB);
        } else if (id == 2) {
            authUrl = authScribeConfig.getAuthUrl(AuthType.VK);
        }
        return "redirect:" + authUrl;
    }

    @GetMapping(value = "/authorize/{id}")
    public ResponseEntity<?> socialAuth(@PathVariable(value = "id") int id, @RequestParam(value = "code") String code,
                                        @RequestParam(value = "error", required = false) String err, HttpServletResponse response) {
        if(err != null){
            return ResponseEntity.status(302).header("Location", "/").build();
        }

        Cookie cookie = null;
        try {
            if (id == 1) {
                cookie = authScribeConfig.authorization(code, AuthType.FB);
            } else if (id == 2) {
                cookie = authScribeConfig.authorization(code, AuthType.VK);
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        response.addCookie(cookie);
        return ResponseEntity.status(302).header("Location", "/").build();
    }

    @GetMapping(value = {"/exit"})
    public String exitAndRemoveCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(CookieHelper.authCookie, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}