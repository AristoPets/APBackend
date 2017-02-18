package com.AristoPets.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/aboutUs")
public class AboutUsController {

    private final HeaderMapper headerMapper;

    @Autowired
    public AboutUsController(HeaderMapper headerMapper) {
        this.headerMapper = headerMapper;
    }

    @GetMapping("/")
    public String getMainPage(HttpServletRequest request, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);
        return "aboutUs";
    }
}
