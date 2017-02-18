package com.AristoPets.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    private final HeaderMapper headerMapper;

    @Autowired
    public ProfileController(HeaderMapper headerMapper) {
        this.headerMapper = headerMapper;
    }

    @GetMapping("/myprofile")
    public String getProfilePage(HttpServletRequest request, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);
        return "profile";
    }
}
