package com.AristoPets.web;

import com.AristoPets.entity.Advert;
import com.AristoPets.services.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    private final AdvertService advertService;
    private final HeaderMapper headerMapper;

    @Autowired
    public IndexController(AdvertService advertService, HeaderMapper headerMapper) {
        this.advertService = advertService;
        this.headerMapper = headerMapper;
    }


    @GetMapping("/")
    public String getIndexPage(HttpServletRequest request, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);
        List<Advert> adverts = advertService.findAll();
        model.addAttribute("adverts", adverts);
        return "index";
    }
}
