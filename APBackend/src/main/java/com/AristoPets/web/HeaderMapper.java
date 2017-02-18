package com.AristoPets.web;


import com.AristoPets.entity.User;
import com.AristoPets.webconfig.filters.AuthFilter;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderMapper {

    // return user if exists
    public User fillHeaderHtmlFragment(HttpServletRequest request, Model model) {

        if (request.getParameter(AuthFilter.NEED_AUTH_PARAM) != null) {
            model.addAttribute("isUserAuth", false);
            model.addAttribute("invalidInput", "Invalid name or password");
            model.addAttribute("needAuth", true);
            return null;
        }

        String regInvalidInput = request.getParameter(AuthFilter.INVALID_REG_PARAM);
        if (regInvalidInput != null) {
            model.addAttribute("isUserAuth", false);
            model.addAttribute("regInvalidInput", regInvalidInput);
            model.addAttribute("openReg", true);
            return null;
        }

        User user = (User) request.getAttribute("userData");
        if (user == null) {
            model.addAttribute("isUserAuth", false);
            return null;
        }

        model.addAttribute("user", user);
        model.addAttribute("isUserAuth", true);
        return user;
    }
}
